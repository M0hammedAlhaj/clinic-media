package com.spring.clinicmedia.domain.port.repository;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends BaseRepository<ClinicDoctorRequest, Long> {

    Optional<ClinicDoctorRequest> findByClinicIdAndDoctorIdAndSender(long clinicId, long doctorId, UserType sender);

    List<ClinicDoctorRequest> getRequestsBySenderIdAndSenderType(long senderId, UserType sender, Pageable pageable);

    Optional<ClinicDoctorRequest> findByClinicIdAndDoctorID(long clinicId, long doctorId);
}
