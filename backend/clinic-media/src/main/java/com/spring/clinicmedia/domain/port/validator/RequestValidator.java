package com.spring.clinicmedia.domain.port.validator;

import com.spring.clinicmedia.domain.model.UserType;

public interface RequestValidator {

    void validateRequestDoesNotExist(long clinicId, long doctorId, UserType senderType);
}
