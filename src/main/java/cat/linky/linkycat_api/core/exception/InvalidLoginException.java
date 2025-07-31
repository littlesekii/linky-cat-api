package cat.linky.linkycat_api.core.exception;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String msg) { super(msg); }
}
