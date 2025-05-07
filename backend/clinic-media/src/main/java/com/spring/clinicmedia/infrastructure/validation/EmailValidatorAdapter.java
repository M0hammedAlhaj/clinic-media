package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.EmailAlreadyExistsException;
import com.spring.clinicmedia.domain.port.repository.RegistrationRepository;
import com.spring.clinicmedia.domain.port.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailValidatorAdapter implements EmailValidator {

    private final RegistrationRepository repository;

    @Override
    public void isValid(String email) {
        repository.findByEmail(email)
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException("Email already exists");
                });
    }

}
