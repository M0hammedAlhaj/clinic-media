package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.result.UserCreationResult;

public class UserCreationResultMapping {

    public static UserCreationResult mapToResult(User user) {
        Registration registration = user.getRegistration();
        return UserCreationResult.builder()
                .userId(user.getUserId())
                .email(registration.getEmail())
                .build();
    }

}
