package cat.linky.linkycat_api.core.exception;

public class InvalidEmailVerificationException extends RuntimeException {
    public InvalidEmailVerificationException(String msg) { 
        super(msg); 
    }
}
