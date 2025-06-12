package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DuplicateRequestByClinicAndDoctorValidator implements RequestValidator {

    private final RequestRepository requestRepository;


    @Override
    public void validateRequestDoesNotExist(long clinicId, long doctorId) {
        requestRepository.findByClinicIdAndDoctorID(clinicId, doctorId).ifPresent(request -> {
            throw new ResourceAlreadyExistsException("Request already exists between clinic and doctor.");
        });

    }
}
