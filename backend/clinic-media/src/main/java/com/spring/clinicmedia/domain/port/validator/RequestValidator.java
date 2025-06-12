package com.spring.clinicmedia.domain.port.validator;

public interface RequestValidator {

    void validateRequestDoesNotExist(long clinicId, long doctorId);
}
