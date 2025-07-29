package cat.linky.linkycat_api.infra.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cat.linky.linkycat_api.core.dto.StandardErrorResponseDTO;
import cat.linky.linkycat_api.core.exception.InvalidAuthCredentialsException;
import cat.linky.linkycat_api.core.exception.InvalidRegisterException;
import cat.linky.linkycat_api.core.exception.NotExistingLoginUsernameException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    /* Custom exceptions */

    @ExceptionHandler(NotExistingLoginUsernameException.class)
    public ResponseEntity<StandardErrorResponseDTO> notExistingLoginUsername(NotExistingLoginUsernameException e, HttpServletRequest request) {
        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(), 
            "AUTH001",
            "Not existing login username",
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
            "AUTH002",
            "Invalid auth credentials",
            e.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(InvalidRegisterException.class)
    public ResponseEntity<StandardErrorResponseDTO> invalidUsername(InvalidRegisterException e, HttpServletRequest request) {
        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(), 
            "AUTH003",
            "Invalid register",
            e.getMessage(), 
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    /***/

    /* Jakarta Validation exceptions */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorResponseDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        String fieldErrorMessage = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        StandardErrorResponseDTO res = new StandardErrorResponseDTO(
            Instant.now(),
            HttpStatus.BAD_REQUEST.value(),
            "VAL001",
            "Method argument not valid",
            fieldErrorMessage,
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
    
    /***/
}
