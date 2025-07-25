package cat.linky.linkycat_api.core.exception;

public class ExistingUserException extends RuntimeException {
    public ExistingUserException(String msg) { super(msg); }
}