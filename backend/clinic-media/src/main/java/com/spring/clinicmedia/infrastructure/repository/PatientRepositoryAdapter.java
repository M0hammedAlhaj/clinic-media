package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.PatientJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Patient> getUserByActive(boolean active, Pageable pageable) {
        return patientJpaRepository.findByIsActive(active, pageable);
    }

    @Override
    public Patient getUserById(Long id) {
        return patientJpaRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.PATIENT, id));

    }

    @Override
    public Patient getUserByUserEmail(String userEmail) {
        return patientJpaRepository.findByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.PATIENT, userEmail));
    }

}
