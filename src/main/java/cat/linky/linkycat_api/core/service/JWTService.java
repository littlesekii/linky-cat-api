package cat.linky.linkycat_api.core.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTService {
    
    @Value("${jwt.secret}")
    private String secret;

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String subject = JWT.require(algorithm)
                .withIssuer("linkycat-api")
                .build()
                .verify(token)
                .getSubject();

            return subject;
        } catch(JWTVerificationException e) {
            return "";
        }
    }

    public String generateToken(String subject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer("linkycat-api")
                .withSubject(subject)
                .withExpiresAt(generateExpiration())
                .sign(algorithm);

            return token;
        } catch(JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    private Instant generateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
