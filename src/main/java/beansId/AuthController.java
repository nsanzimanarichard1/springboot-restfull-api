package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          CustomUserDetailsService customUserDetailsService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

    // login
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDto request
    ) {
        System.out.println("LOGIN USERNAME = [" + request.username() + "]");
        System.out.println("LOGIN PASSWORD LENGTH = " +
                (request.password() == null ? "null" : request.password().length()));

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.username(),
                                request.password()
                        )
                );

        System.out.println("AUTHENTICATION SUCCESSFUL");

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        System.out.println("USER DETAILS USERNAME = [" +
                userDetails.getUsername() + "]");

        String token = jwtService.generateToken(userDetails);

        System.out.println("JWT GENERATED");

        return ResponseEntity.ok("Your token Generated, verify: "+token);
    }
    // testing login
    @GetMapping("/test-password")
    public ResponseEntity<String> testPassword() {

        User user = userRepository.findByUsername("test")
                .orElseThrow(() -> new RuntimeException("test user not found"));

        boolean matches = passwordEncoder.matches(
                "test1234",
                user.getPassword()
        );

        return ResponseEntity.ok("Password matches: " + matches);
    }

    //test aunthentication
    @GetMapping("/test-authentication")
    public ResponseEntity<String> testAuthentication() {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                "test",
                                "test1234"
                        )
                );

        return ResponseEntity.ok(
                "Authentication successful: "
                        + authentication.getName()
        );
    }
}