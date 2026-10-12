package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1") // Base path for all methods in this controller
public class Controller {

    private  final StudentRepository studentRepository;

    public Controller(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
    public Student createStudent(@RequestBody Student student){

        Student student1 = studentRepository.save(student);
        System.out.println("student id is:  " + student1.getId());
        System.out.println("student firstname is:  " + student1.getFirstname());
        System.out.println("student lastname is " + student1.getLastname());
        System.out.println("student email is:  " + student1.getEmail());
        System.out.println("Student age is: " + student1.getAge());
        return student1;


    }
}