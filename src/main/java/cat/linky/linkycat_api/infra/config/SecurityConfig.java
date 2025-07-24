package cat.linky.linkycat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private final SecurityFilter securityFilter;

    // SecurityConfig(SecurityFilter securityFilter) {
    //     this.securityFilter = securityFilter;
    // }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(options -> options.sameOrigin()))   
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize

            // .requestMatchers(PathRequest.toH2Console()).permitAll()
            // .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
            // .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
            // .requestMatchers(HttpMethod.GET, "/api/ping").authenticated()
                .anyRequest().permitAll()
            )        
                 
            // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
