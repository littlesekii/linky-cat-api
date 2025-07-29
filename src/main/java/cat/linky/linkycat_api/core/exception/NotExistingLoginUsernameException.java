package cat.linky.linkycat_api.core.exception;

public class NotExistingLoginUsernameException extends RuntimeException{
    public NotExistingLoginUsernameException() {
        super("There is no existing account with this username");
    }
    public NotExistingLoginUsernameException(String msg) { 
        super(msg); 
    }
}
