package com.spring.clinicmedia.infrastructure.factory.user;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificUserCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.infrastructure.mapping.UserCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class DoctorUserCreator implements SpecificUserCreator {
    @Override
    public UserType getUserType() {
        return UserType.DOCTOR;
    }

    @Override
    public User create(UserCreationCommand command) {
        User user = new Doctor();
        UserCreationCommandMapper.setRegistration(command, user);
        return user;
    }
}
