package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.EmailAlreadyExistsException;
import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.repository.RegistrationRepository;
import com.spring.clinicmedia.domain.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adapter implementation of the {@link EmailValidator} interface responsible for
 * validating that a given email is not already registered in the system.
 * <p>
 * This class delegates to the {@link RegistrationRepository} to check for email uniqueness,
 * and throws an {@link com.spring.clinicmedia.domain.exception.EmailAlreadyExistsException}
 * if the email is already in use.
 * </p>
 */
@Component
@AllArgsConstructor
public class EmailValidatorAdapter implements EmailValidator {

    private final RegistrationRepository repository;

    /**
     * Validates whether the provided email is unique in the system.
     *
     * @param email the email to validate
     * @throws EmailAlreadyExistsException if the email is already registered
     */
    @Override
    public void isValid(String email) {
        repository.findByEmail(email)
                .ifPresent(user -> {
                    throw new ResourceAlreadyExistsException("Email already exists by ", email);
                });
    }
}

