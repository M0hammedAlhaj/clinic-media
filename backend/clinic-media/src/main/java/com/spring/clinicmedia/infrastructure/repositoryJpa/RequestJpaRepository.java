package com.spring.clinicmedia.infrastructure.repositoryJpa;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestJpaRepository extends JpaRepository<Request, Long> {


    Optional<Request> findByClinicUserIdAndDoctorUserIdAndSender(Long clinic_userId, Long doctor_userId, UserType sender);
}
