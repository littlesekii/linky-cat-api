package cat.linky.linkycat_api.core.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cat.linky.linkycat_api.core.exception.InvalidAuthCredentialsException;
import cat.linky.linkycat_api.core.exception.InvalidRegisterException;
import cat.linky.linkycat_api.core.model.User;

@Service
public class AuthService {

    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(UserService userService, JWTService jwtService, AuthenticationManager authManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public String authenticate(String username, String password) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
            UserDetails userDetails = (UserDetails) authManager.authenticate(usernamePassword).getPrincipal();

            return jwtService.generateToken(userDetails.getUsername());
        } catch (RuntimeException e) {
            throw new InvalidAuthCredentialsException();
        }
    }

    public void register(User user) {

        validateUsername(user.getUsername());
        validatePassword(user.getPassword());

        if (userService.findByUsername(user.getUsername()) != null)
            throw new InvalidRegisterException("An account with this username is already registered");

        if (userService.findByEmail(user.getEmail()) != null)
            throw new InvalidRegisterException("An account with this email is already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userService.insert(user);
    }

    private void validateUsername(String username) {
        
        if (username.length() > 32) 
            throw new InvalidRegisterException("Username length can't be over 32 characters");

        if (username.matches(".*[^A-Za-z0-9._].*"))
            throw new InvalidRegisterException("Username can only contain letters (A-Z, a-z), numbers, underscores, and periods");

        if (!username.matches(".*[A-Za-z0-9].*"))
            throw new InvalidRegisterException("Username must contain at least one letter (A-Z, a-z) or number");

        if (username.contains(".."))
            throw new InvalidRegisterException("Username can't contain more than one period in a row");

        if (username.startsWith(".") || username.endsWith(".")) {
            throw new InvalidRegisterException("Username can't start or end with a period");
        }
    }

    private void validatePassword(String password) {

        if (password.length() < 8) {
            throw new InvalidRegisterException("Password length must contain at least 8 characters");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidRegisterException("Password must contain at least one uppercase letter (A-Z)");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new InvalidRegisterException("Password must contain at least one lowercase letter (a-z)");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new InvalidRegisterException("Password must contain at least one number");
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*")) {
            throw new InvalidRegisterException("Password must contain at least one special character (e.g. @, ! or #)");
        }
    }
    
}
