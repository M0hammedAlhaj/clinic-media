package com.spring.clinicmedia.domain.factory;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;

public interface SpecificUserCreator {

    UserType getUserType();

    User create(UserCreationCommand command);
}
