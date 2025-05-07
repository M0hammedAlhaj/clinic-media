package com.spring.clinicmedia.infrastructure.jwt.claim;

import com.spring.clinicmedia.domain.port.validator.ExtractClaim;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class ExtractClaimEmail extends ExtractExecution<String> {

    protected ExtractClaimEmail(ExtractClaim extractClaim) {
        super(extractClaim);
    }

    @Override
    public String execute(String token) {
        return extractClaim.extractClaim(token, Claims::getSubject);
    }

}
