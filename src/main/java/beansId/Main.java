package beansId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

    }

    @Bean
    CommandLineRunner seedAdminRole(RoleRepository roleRepository) {
        return args -> roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
    }

}
