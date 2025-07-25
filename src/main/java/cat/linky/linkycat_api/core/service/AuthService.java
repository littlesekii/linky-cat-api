package cat.linky.linkycat_api.core.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cat.linky.linkycat_api.core.exception.ExistingUserException;
import cat.linky.linkycat_api.core.exception.InvalidAuthCredentialsException;
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

        if (userService.findByUsername(user.getUsername()) != null)
            throw new ExistingUserException("An account with this username is already registered");

        if (userService.findByEmail(user.getEmail()) != null)
            throw new ExistingUserException("An account with this email is already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userService.insert(user);
    }
    
}
