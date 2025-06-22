package com.spring.clinicmedia.infrastructure.validation.jwt;

import com.spring.clinicmedia.domain.Handler;
import com.spring.clinicmedia.domain.repository.RegistrationRepository;
import com.spring.clinicmedia.infrastructure.jwt.BaseHandler;
import com.spring.clinicmedia.infrastructure.jwt.claim.ExtractClaimEmail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailValidatorJwt extends BaseHandler<String> {

    private final ExtractClaimEmail extractClaimEmail;

    private final RegistrationRepository repository;

    public EmailValidatorJwt(ExtractClaimEmail extractClaimEmail,
                             RegistrationRepository repository,
                             @Qualifier(value = "validatorDateJwt") Handler<String> handler) {
        this.extractClaimEmail = extractClaimEmail;
        this.repository = repository;
        setNext(handler);
    }

    @Override
    public void handle(String token) {
        String email = extractClaimEmail.execute(token);
        repository.getByEmail(email);
        getNext().handle(token);
    }
}
