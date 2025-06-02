package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DuplicateRequestByClinicAndDoctorValidator implements RequestValidator {

    private final RequestRepository requestRepository;


    @Override
    public void validateRequestDoesNotExist(long clinicId, long doctorId, UserType senderType) {
        requestRepository.findByClinicIdAndDoctorID(clinicId, doctorId);
    }
}
