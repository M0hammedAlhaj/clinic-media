package com.spring.clinicmedia.infrastructure.factory.user;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificUserCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.infrastructure.mapping.UserCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class LabUserCreator implements SpecificUserCreator {
    @Override
    public UserType getUserType() {
        return UserType.LAB;
    }

    @Override
    public User create(UserCreationCommand command) {
        Lab lab = new Lab();
        UserCreationCommandMapper.setRegistration(command, lab);
        return lab;

    }
}
