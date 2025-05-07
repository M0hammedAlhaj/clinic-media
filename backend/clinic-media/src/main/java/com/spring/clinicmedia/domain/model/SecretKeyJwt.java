package com.spring.clinicmedia.domain.model;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Data
@Component
public class SecretKeyJwt {

    private SecretKey key;

    @PostConstruct
    public void init() {
        String base64Key = "2XwD8eRxMvT4n9VpLF9UyBG5cD7NlKmKqY6fO9qxMzU=";
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));
    }

}
