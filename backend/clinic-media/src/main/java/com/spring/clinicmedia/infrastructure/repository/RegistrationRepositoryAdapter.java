package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.port.repository.RegistrationRepository;
import com.spring.clinicmedia.infrastructure.Jpa.RegistrationJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationRepositoryAdapter implements RegistrationRepository {

    private final RegistrationJpaRepository repository;

    @Override
    public Optional<Registration> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Registration getByEmail(String email) {

        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourcesNotFoundException("Email not found"));
    }
}
