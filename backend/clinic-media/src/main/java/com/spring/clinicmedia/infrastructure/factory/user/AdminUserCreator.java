package com.spring.clinicmedia.infrastructure.factory.user;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.SpecificUserCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Admin;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.infrastructure.mapping.UserCreationCommandMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminUserCreator implements SpecificUserCreator {
    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    public User create(UserCreationCommand command) {
        Admin admin = new Admin();
        UserCreationCommandMapper.setRegistration(command, admin);
        admin.setActive(true);
        return admin;
    }

}
