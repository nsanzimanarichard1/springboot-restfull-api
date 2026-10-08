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
    CommandLineRunner order(StudentRepository studentRepository) {
        return _ -> {

            Student student = new Student(
                    "Richard",
                    "Nsanzimana",
                    "richard@example.com",
                    22
            );

            studentRepository.save(student);

        };
    }
}