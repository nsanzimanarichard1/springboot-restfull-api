package beansId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1") // Base path for all methods in this controller
public class HelloController {

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
}