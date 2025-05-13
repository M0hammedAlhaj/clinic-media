package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.RequestAlreadyExistsException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adapter implementation of the {@link RequestValidator} interface that checks
 * whether a request between a doctor and a clinic already exists.
 * <p>
 * This component validates uniqueness constraints before persisting a new request,
 * throwing a {@link com.spring.clinicmedia.domain.exception.RequestAlreadyExistsException}
 * if a duplicate is found.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class RequestValidatorAdapter implements RequestValidator {

    private final RequestRepository requestRepository;

    /**
     * Validates that a request between the given doctor and clinic
     * with the specified sender type does not already exist.
     *
     * @param clinicId   the ID of the clinic
     * @param doctorId   the ID of the doctor
     * @param senderType the type of the user sending the request
     * @throws RequestAlreadyExistsException if a matching request already exists
     */
    @Override
    public void validateRequestDoesNotExist(long clinicId, long doctorId, UserType senderType) {
        requestRepository.findByClinicIdAndDoctorIdAndSender(clinicId, doctorId, senderType)
                .ifPresent(request -> {
                    throw new RequestAlreadyExistsException(
                            "Request already exists for clinicId=" + clinicId +
                                    ", doctorId=" + doctorId +
                                    ", sender=" + senderType
                    );
                });
    }
}
