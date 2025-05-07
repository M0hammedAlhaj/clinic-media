package com.spring.clinicmedia.infrastructure.factory.user;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificUserCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.infrastructure.mapping.UserCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class ClinicUserCreator implements SpecificUserCreator {
    @Override
    public UserType getUserType() {
        return UserType.CLINIC;
    }

    @Override
    public User create(UserCreationCommand command) {
        Clinic clinic = new Clinic();
        UserCreationCommandMapper.setRegistration(command, clinic);
        return clinic;
    }
}
