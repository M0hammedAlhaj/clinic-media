package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestJpaRepository extends JpaRepository<Request, Long> {


    Optional<Request> findByClinicUserIdAndDoctorUserIdAndSender(Long clinic_userId,
                                                                 Long doctor_userId,
                                                                 UserType sender);

    Page<Request> findRequestsByClinicUserIdAndSender(long senderId,
                                                      UserType sender,
                                                      Pageable pageable);

    Page<Request> findRequestsByDoctorUserIdAndSender(long senderId,
                                                      UserType sender,
                                                      Pageable pageable);

    Optional<Request> findByClinicUserIdAndDoctorUserId(long clinicId, long doctorId);
}
