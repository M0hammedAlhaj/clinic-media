package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.result.UserCreationResult;
import com.spring.clinicmedia.presentation.dto.createAccount.UserCreationRequest;
import com.spring.clinicmedia.presentation.dto.createAccount.UserCreationResponse;

public class UserCreationMapper {

    private UserCreationMapper() {

    }

    public static UserCreationCommand toCommand(UserCreationRequest request) {

        checkPasswordConfirm(request.getPassword(), request.getConfirmPassword());

        return UserCreationCommand.builder()
                .name(request.getName())
                .userType(request.getUserType())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    private static void checkPasswordConfirm(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    public static UserCreationResponse toResponse(UserCreationResult result) {
        return UserCreationResponse.builder()
                .userId(result.getUserId())
                .email(result.getEmail())
                .build();
    }
}
