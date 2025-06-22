package com.spring.clinicmedia.domain.validator;

import com.spring.clinicmedia.domain.model.UserType;

public interface UserActivationValidator {

    void validateUserIsActive(long userId, UserType userType);

}
