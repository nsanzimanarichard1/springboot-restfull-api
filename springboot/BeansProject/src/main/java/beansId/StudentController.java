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
    public StudentDto createStudent(@RequestBody StudentDto studentDto){


        // find schoolID in database
        School school = schoolRepository.findById(studentDto.schoolId())
                .orElseThrow( () -> new RuntimeException
                        ("there is no school in database with that id: " + studentDto.schoolId())
                );



        Student student1 = studentRepository.save();

        // create entity studet
        student1.setFirstname(studentDto.firstname());
        student1.setLastname(studentDto.lastname());
        student1.setEmail(studentDto.email());

        // connect student


        System.out.println("student id is:  " + student1.getId());
        System.out.println("student firstname is:  " + student1.getFirstname());
        System.out.println("student lastname is " + student1.getLastname());
        System.out.println("student email is:  " + student1.getEmail());
        System.out.println("Student age is: " + student1.getAge());
        return student1;

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