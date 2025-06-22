package com.spring.clinicmedia.infrastructure.jwt;

import com.spring.clinicmedia.domain.model.SecretKeyJwt;
import com.spring.clinicmedia.domain.jwt.GenerateToken;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class GenerateTokenAdapter implements GenerateToken {

    private final SecretKeyJwt secretKey;


    @Override
    public String generateToken(String userEmail) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(userEmail)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 1000))
                .and()
                .signWith(secretKey.getKey())
                .compact();
    }

}
