package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RequestCreationService {

    private final RequestRepository requestRepository;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final RequestValidator validator;

    @Transactional
    public void createRequest(long doctorId, long clinicId, UserType senderType) {
        Clinic clinic = clinicRepository.getUserById(clinicId);
        Doctor doctor = doctorRepository.getUserById(doctorId);

        validator.validateRequestDoesNotExist(clinicId, doctorId, senderType);

        Request request = buildRequest(doctor, clinic, senderType);

        requestRepository.save(request);
    }

    private Request buildRequest(Doctor doctor, Clinic clinic, UserType senderType) {
        return Request.builder()
                .clinic(clinic)
                .doctor(doctor)
                .sender(senderType)
                .status(RequestStatus.PENDING)
                .build();
    }
}

