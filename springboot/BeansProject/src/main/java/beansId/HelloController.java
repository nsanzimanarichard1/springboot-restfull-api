package beansId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/api/v1")

    public String Greetings(){
        return "hi there are you good?";
    }

    @GetMapping("api/v2")
    public String sum(){
        int a=10;
        int b=20;
        int c=a+b;
        return "addition of two number is:"+c;
    }
}
