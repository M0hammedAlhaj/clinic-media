package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.UserAccountNotActivation;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.repository.user.UserRepository;
import com.spring.clinicmedia.domain.validator.UserActivationValidator;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import org.springframework.stereotype.Component;

@Component
public class UserActivationValidatorAdapter implements UserActivationValidator {


    private final UserRepositoryDispatcher userRepositoryDispatcher;

    public UserActivationValidatorAdapter(UserRepositoryDispatcher userRepositoryDispatcher) {
        this.userRepositoryDispatcher = userRepositoryDispatcher;
    }

    @Override
    public void validateUserIsActive(long userId, UserType userType) {

        UserRepository<User> userRepository =
                userRepositoryDispatcher.getRepository(userType);

        User user = userRepository.getUserByIdOrElseThrow(userId);

        if (!user.isActive()) {
            throw new UserAccountNotActivation("User Account is not activated");
        }
    }
}
