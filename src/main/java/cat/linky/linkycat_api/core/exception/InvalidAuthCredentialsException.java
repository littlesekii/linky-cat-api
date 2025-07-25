package cat.linky.linkycat_api.core.exception;

public class InvalidAuthCredentialsException extends RuntimeException {
    public InvalidAuthCredentialsException() {
        super("Invalid authentication credentials");
    }
    public InvalidAuthCredentialsException(String msg) { 
        super(msg); 
    }
}
