package cat.linky.linkycat_api.core.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//THIS CLASS IS USED BY SPRING SECURITY AUTHENTICATION MANAGER (AUTOMATICALLY)
@Service
public class SSAuthService implements UserDetailsService {

    private final UserService userService;

    public SSAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username);
    }
}
