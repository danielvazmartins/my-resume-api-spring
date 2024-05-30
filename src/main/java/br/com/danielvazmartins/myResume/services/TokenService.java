package br.com.danielvazmartins.myResume.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.danielvazmartins.myResume.entities.User;

@Service
public class TokenService {
    private final String tokenSecret = "88cc410063f501770eaf6928281b3005";
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        return JWT.create()
            .withIssuer("myResume")
            .withSubject(user.getUsername())
            .withExpiresAt(genExpirationDate())
            .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
        return JWT.require(algorithm)
            .withIssuer("myResume")
            .build()
            .verify(token)
            .getSubject();
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.ofHours(-3));
    }
}
