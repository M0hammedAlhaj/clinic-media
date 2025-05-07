package com.spring.clinicmedia.domain.port.validator;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface ExtractClaim {


    <T> T extractClaim(String token,
                       Function<Claims, T> claimsResolver);

}
