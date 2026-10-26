package beansId;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1") // Base path for all methods in this controller
public class StudentController {

    private  final StudentRepository studentRepository;
    private  final  SchoolRepository schoolRepository;
    private  final  StudentService studentService;

    public StudentController(
            StudentRepository studentRepository,
            SchoolRepository schoolRepository,
            StudentService studentService
            )
    {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
        this.studentService = studentService;
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
    public StudentResponseDto createStudents(
            @Valid @RequestBody StudentDto studentDto ) {

        // call student service here
       return studentService.createStudentServiceMethod(studentDto);
    }


    // GetAll Student
    @GetMapping("/student/{id}")
   public Student findStudentById( @Valid @PathVariable Integer id ) {
        return studentService.getStudentById(id);

    }

    // getAll student
    @GetMapping("/students")
    public List<Student> PrintAllStudent (){
        return studentService.getAllStudent();
    }

    // Update Student by id

    @PatchMapping("/update/student/{id}")
  public Student updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // delete student by id
    @DeleteMapping("/delete/student/{id}")
    public String deleteStudentBYId(@PathVariable Integer id){
        return  studentService.deleteStudent(id);

    }
}