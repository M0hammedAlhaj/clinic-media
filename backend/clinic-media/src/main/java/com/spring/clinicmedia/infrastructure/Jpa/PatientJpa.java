package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface PatientJpa extends JpaRepository<Patient, Long> {

    Optional<Patient> findByRegistrationEmail(String email);


    Page<Patient> findByIsActive(boolean active, Pageable pageable);

    boolean existsPatientByUserIdAndInsurancesInsuranceName(Long patientId, String insuranceName);
}
