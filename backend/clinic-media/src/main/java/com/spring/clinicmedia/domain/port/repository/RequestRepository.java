package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RequestRepository {

    void save(Request request);

    Optional<Request> findByClinicIdAndDoctorIdAndSender(long clinicId, long doctorId, UserType sender);

    Page<Request> findRequestsBySenderIdAndSenderType(long senderId, UserType sender, Pageable pageable);
}
