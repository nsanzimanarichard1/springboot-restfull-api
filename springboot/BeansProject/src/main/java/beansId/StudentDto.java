package beansId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record StudentDto(

        @NotEmpty(message = "first name is required")
        String firstname,

        @NotEmpty(message = "last name is required")
        String lastname,

        @Email(message = "email must be valid")
        String email,
        @NotNull(message = "id should be provided")
        Integer schoolId
                          ) {


}
