package example.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/custom")
    public String custom() {
        return "New custom method!!";
    }

    @RequestMapping("/additional")
    public String additional() {
        return "Wow wow additional new method!!!";
    }
    
}
