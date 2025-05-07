package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import com.spring.clinicmedia.domain.port.repository.LabRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.LabJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LabRepositoryAdapter implements LabRepository {

    private final LabJpaRepository labJpaRepository;


    @Override
    public Optional<Lab> findUserByEmail(String email) {
        return labJpaRepository.findLabByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Lab user) {
        labJpaRepository.save(user);
    }

}

