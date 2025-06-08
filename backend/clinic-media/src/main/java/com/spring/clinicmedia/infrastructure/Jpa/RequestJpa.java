package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestJpaRepository extends JpaRepository<ClinicDoctorRequest, Long> {


    Optional<ClinicDoctorRequest> findByClinicUserIdAndDoctorUserIdAndSender(Long clinic_userId,
                                                                             Long doctor_userId,
                                                                             UserType sender);

    Page<ClinicDoctorRequest> findRequestsByClinicUserIdAndSender(long senderId,
                                                                  UserType sender,
                                                                  Pageable pageable);

    Page<ClinicDoctorRequest> findRequestsByDoctorUserIdAndSender(long senderId,
                                                                  UserType sender,
                                                                  Pageable pageable);

    Optional<ClinicDoctorRequest> findByClinicUserIdAndDoctorUserId(long clinicId, long doctorId);
}
