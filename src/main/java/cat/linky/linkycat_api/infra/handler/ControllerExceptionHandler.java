package cat.linky.linkycat_api.infra.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cat.linky.linkycat_api.core.dto.StandardErrorResponseDTO;
import cat.linky.linkycat_api.core.exception.ExistingUserException;
import cat.linky.linkycat_api.core.exception.InvalidAuthCredentialsException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    /* Custom exceptions */

    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<StandardErrorResponseDTO> existingUser(ExistingUserException e, HttpServletRequest request) {
        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(), 
            "Existing user",
            e.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(InvalidAuthCredentialsException.class)
    public ResponseEntity<StandardErrorResponseDTO> invalidAuthCredentials(InvalidAuthCredentialsException e, HttpServletRequest request) {
        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(), 
            "Invalid auth credentials",
            e.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /***/

    /* Validation exceptions */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        String fieldErrorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Method argument not valid",
            fieldErrorMessage,
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
    
    /***/
}
