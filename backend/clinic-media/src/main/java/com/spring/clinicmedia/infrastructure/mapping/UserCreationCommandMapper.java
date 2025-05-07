package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.model.enitity.user.User;

public class UserCreationCommandMapper {


    public static void setRegistration(UserCreationCommand command, User user) {
        user.setRegistration(Registration.builder()
                .email(command.getEmail())
                .name(command.getName())
                .password(command.getPassword())
                .build());
    }
}
