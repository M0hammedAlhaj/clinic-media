package com.spring.clinicmedia.infrastructure.jwt.claim;

import com.spring.clinicmedia.domain.jwt.ExtractClaim;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExtractClaimDate extends ExtractExecution<Date> {
    protected ExtractClaimDate(ExtractClaim extractClaim) {
        super(extractClaim);
    }

    @Override
    public Date execute(String token) {
        return extractClaim.extractClaim(token, Claims::getExpiration);
    }

}
