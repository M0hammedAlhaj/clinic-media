package com.spring.clinicmedia.domain.factory;

import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.model.enitity.user.User;

public interface UserFactory extends Creator<User, UserCreationCommand> {

    User create(UserCreationCommand userCreationCommand);
}
