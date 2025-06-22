package com.spring.clinicmedia.domain.validator;

public interface RequestValidator {

    void validateRequestDoesNotExist(long clinicId, long doctorId);
}
