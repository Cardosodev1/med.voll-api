package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var error = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(ValidationErrorDTO::new).toList());
    }

    private record ValidationErrorDTO(String field, String message) {
        public ValidationErrorDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
