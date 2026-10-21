package beansId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Cross-Site Request Forgery) because we are building a stateless REST API tested via Postman
                .csrf(csrf -> csrf.disable())

                // 2. Define rule-based access for different endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll() // Example: Public endpoints (login/register) don't need a password
                        .anyRequest().authenticated()                   // All other endpoints require authentication
                )

                // 3. Enable HTTP Basic authentication (allows passing username & password in headers/Postman)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}