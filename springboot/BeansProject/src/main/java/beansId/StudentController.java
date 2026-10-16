package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1") // Base path for all methods in this controller
public class StudentController {

    private  final StudentRepository studentRepository;
    private  final  SchoolRepository schoolRepository;

    public StudentController(
            StudentRepository studentRepository,
            SchoolRepository schoolRepository
            )
    {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }


    // Maps to: GET http://localhost:8080/api/v1
    @GetMapping
    public String Greetings() {
        return "Hi there are you good?";
    }

    // Maps to: GET http://localhost:8080/api/v1/hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }


    @PostMapping("/post")
    public String create(@RequestBody String message)
    {
        return "your message is " + message;

    }

    // create order by post http://localhost:8080/api/v1/create-order
    @PostMapping("/create-order")
    public ResponseEntity<Order> CreateOrder(@RequestBody Order order){

        return  ResponseEntity.ok(order);
    }

    // java record

    @PostMapping("/create-order-record")
    public ResponseEntity<OrderRecord> CreateOrderRecord(@RequestBody OrderRecord order){
        return ResponseEntity.ok(order);
    }



    // Maps to: GET http://localhost:8080/api/v1/hello
    @GetMapping("/firsName&lastName")
    public String filteringAndPagination(
            @RequestParam("firstName") String firstName,
            @RequestParam ("lastName") String lastName
    ) {


        return "Your name is:" +firstName + " " + lastName;
    }



    // student manipulation data
    @PostMapping("/student")
    public StudentResponseDto createStudent(
            @RequestBody StudentDto studentDto) {

        // Find school using schoolId
        School school = schoolRepository.findById(studentDto.schoolId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "There is no school in database with that id: "
                                        + studentDto.schoolId()
                        )
                );

        // Create Student entity
        Student student1 = new Student();

        student1.setFirstname(studentDto.firstname());
        student1.setLastname(studentDto.lastname());
        student1.setEmail(studentDto.email());
        student1.setSchool(school);

        // Save student
        Student student = studentRepository.save(student1);

        System.out.println("student firstname is: " + student.getFirstname());
        System.out.println("student lastname is: " + student.getLastname());
        System.out.println("student email is: " + student.getEmail());

        // Return only what we want the user to see
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }
    // GetAll Student
    @GetMapping("/student/{id}")
    public Student getStudentById( @PathVariable Integer id){
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("student with that id does not exist: " + id));
    }

    // getAll student
    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return  studentRepository.findAll();
    }

    // Update Student by id

    @PatchMapping("/update/student/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student doesExist =  studentRepository.findById(id)
                .orElseThrow(
                () -> new RuntimeException("student with that id does not exist: " + id)
        );

        if (student.getFirstname()!=null){
            doesExist.setFirstname(student.getFirstname());
        }

        if (student.getLastname()!=null){
            doesExist.setLastname(student.getLastname());
        }
        if (student.getEmail()!=null){
            doesExist.setEmail(student.getEmail());
        }
        if (student.getAge()!=null){
            doesExist.setAge(student.getAge());
        }


        return  studentRepository.save(doesExist);
    }

    // delete student by id
    @DeleteMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable Integer id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("student with that id does not exist: " + id)
        );
        studentRepository.delete(student);
        return "Student with ID " + id + " deleted successfully";
    }
}