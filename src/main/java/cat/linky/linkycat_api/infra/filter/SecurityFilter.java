package cat.linky.linkycat_api.infra.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cat.linky.linkycat_api.core.service.JWTService;
import cat.linky.linkycat_api.core.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    public SecurityFilter(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
        @SuppressWarnings("null") HttpServletRequest request, 
        @SuppressWarnings("null") HttpServletResponse response, 
        @SuppressWarnings("null") FilterChain filterChain
    ) throws ServletException, IOException {    
        validateRequestAuthentication(request);        
        filterChain.doFilter(request, response);
    }

    private void validateRequestAuthentication(HttpServletRequest request) {
        String requestToken = extractRequestAuthToken(request);

        if (requestToken != null) {
            String username = jwtService.validateToken(requestToken);
            UserDetails user = userService.findByUsername(username);

            if (user != null) {            
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);        
            }
        }
    }

    private String extractRequestAuthToken(HttpServletRequest req) {
        String reqAuthHeader = req.getHeader("Authorization");

        if (reqAuthHeader == null)
            return null;

        reqAuthHeader = reqAuthHeader.replace("Bearer ", "");
        return reqAuthHeader;
    }
    
}
