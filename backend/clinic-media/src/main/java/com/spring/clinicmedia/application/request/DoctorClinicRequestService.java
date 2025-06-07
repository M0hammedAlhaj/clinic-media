package com.spring.clinicmedia.application.request;

import com.spring.clinicmedia.domain.exception.request.RequestAlreadyExistsException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DoctorClinicRequestService {

    private final RequestRepository requestRepository;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final RequestValidator validator;
    private final UserActivationValidator userActivationValidator;

    @Transactional
    public void createRequest(long doctorId,
                              long clinicId,
                              UserType senderType) {

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);
        Doctor doctor = doctorRepository.getUserByIdOrElseThrow(doctorId);


        userActivationValidator.validate(doctorId, UserType.DOCTOR);

        userActivationValidator.validate(clinicId, UserType.CLINIC);

        if (doctorRepository.existsDoctorInClinic(doctorId, clinicId)) {
            throw new RequestAlreadyExistsException("Request Already Exists");
        }

        validator.validateRequestDoesNotExist(clinicId, doctorId, senderType);

        ClinicDoctorRequest clinicDoctorRequest = buildRequest(doctor, clinic, senderType);

        requestRepository.save(clinicDoctorRequest);
    }

    private ClinicDoctorRequest buildRequest(Doctor doctor, Clinic clinic, UserType senderType) {
        return ClinicDoctorRequest.builder()
                .clinic(clinic)
                .doctor(doctor)
                .sender(senderType)
                .status(RequestStatus.PENDING)
                .build();
    }
}

