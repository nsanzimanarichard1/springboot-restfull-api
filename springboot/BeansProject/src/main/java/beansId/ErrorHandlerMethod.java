package beansId;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlerMethod {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> fixError (
            MethodArgumentNotValidException exp ){
        Map<String, String> errors = new HashMap<>();

        exp.getBindingResult().getFieldErrors().forEach( er -> errors.put(
                er.getField(), er.getDefaultMessage()
        )
        );

        ValidationErrorResponse response = new ValidationErrorResponse(
                400,
                "validation field",
                errors
        );

        return  ResponseEntity.badRequest().body(response);
    }
}
