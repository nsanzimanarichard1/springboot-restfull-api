package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Inject RoleRepository
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    // 1. Register endpoint to save a user with a hashed password and a role
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) {
        // Hash the password before saving it to the database!
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Find the default role (make sure this role exists in your 'roles' table)
        Role defaultRole = roleRepository.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("Default role ROLE_STUDENT not found"));

        // Create the user with username, hashed password, and role
        User newUser = new User(request.getUsername(), hashedPassword, defaultRole);
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

    // Get all users endpoint
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsersCreate() {
        List<User> users = customUserDetailsService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}