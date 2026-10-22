package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. Register endpoint to save a user with a BCrypt hashed password
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) {
        // Hash the password before saving it to the database!
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = new User(request.getUsername(), hashedPassword);
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }
}