package com.spring.clinicmedia.infrastructure.jwt.claim;

import com.spring.clinicmedia.domain.model.SecretKeyJwt;
import com.spring.clinicmedia.domain.port.jwt.ExtractClaim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExtractClaimAdapter implements ExtractClaim {

    private final SecretKeyJwt secretKeyJwt;

    public ExtractClaimAdapter(SecretKeyJwt secretKeyJwt) {
        this.secretKeyJwt = secretKeyJwt;
    }

    @Override
    public <T> T extractClaim(String token,
                              Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKeyJwt.getKey())
                .build().parseSignedClaims(token).getPayload();
    }
}
