package com.example.springmovie.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springmovie.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    public static final String USERNAME_KEY = "USERNAME";

    @Value("JTW_SECRET_KEY")
    private String key;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expireTime}")
    private int expireTime;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(key);
    }

    public String generateJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expireTime)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
