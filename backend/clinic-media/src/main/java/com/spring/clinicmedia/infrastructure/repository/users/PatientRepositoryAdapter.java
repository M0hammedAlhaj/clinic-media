package com.spring.clinicmedia.infrastructure.repository.users;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.repository.user.PatientRepository;
import com.spring.clinicmedia.infrastructure.Jpa.users.PatientJpa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientRepositoryAdapter implements PatientRepository {

    private final PatientJpa patientJpa;

    @Override
    public Optional<Patient> findUserByEmail(String email) {
        return patientJpa.findByRegistrationEmail(email);
    }

    @Override
    public Patient saveUser(Patient user) {
        return patientJpa.save(user);
    }

    public Page<Patient> getUserByActive(boolean active, Pageable pageable) {
        return patientJpa.findByIsActive(active, pageable);
    }

    @Override
    public Patient getUserByIdOrElseThrow(Long id) {
        return patientJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(UserType.PATIENT, id));

    }

    @Override
    public Patient getUserByUserEmailOrElseThrow(String userEmail) {
        return patientJpa.findByRegistrationEmail(userEmail)
                .orElseThrow(() -> new ResourcesNotFoundException(Patient.class, userEmail));
    }

    @Override
    public boolean existsByUserId(Long id) {
        return patientJpa.existsById(id);
    }

    @Override
    public boolean isPatientHasInsurance(Long patientId, String insuranceName) {
        return patientJpa.existsPatientByUserIdAndInsurancesInsuranceName(patientId, insuranceName);
    }

}
