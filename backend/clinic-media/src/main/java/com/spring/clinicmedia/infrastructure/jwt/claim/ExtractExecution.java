package com.spring.clinicmedia.infrastructure.jwt.claim;

import com.spring.clinicmedia.domain.port.validator.ExtractClaim;

public abstract class ExtractExecution<T> {

    protected final ExtractClaim extractClaim;

    protected ExtractExecution(ExtractClaim extractClaim) {
        this.extractClaim = extractClaim;
    }

    public abstract T execute(String token);

}
