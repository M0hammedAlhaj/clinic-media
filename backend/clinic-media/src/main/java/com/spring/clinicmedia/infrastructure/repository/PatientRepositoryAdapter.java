package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.PatientJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpaRepository patientJpaRepository;

    @Override
    public Optional<Patient> findUserByEmail(String email) {
        return patientJpaRepository.findByRegistrationEmail(email);
    }

    @Override
    public void saveUser(Patient user) {
        patientJpaRepository.save(user);
    }

}
