package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.ClinicJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicRepositoryAdapter implements ClinicRepository {

    private final ClinicJpaRepository clinicJpaRepository;


    @Override
    public Optional<Clinic> findUserByEmail(String email) {
        return clinicJpaRepository.findClinicByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Clinic user) {
        clinicJpaRepository.save(user);
    }

}

