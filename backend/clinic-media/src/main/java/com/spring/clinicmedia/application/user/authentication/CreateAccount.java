package com.spring.clinicmedia.application.user.authentication;


import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.factory.UserFactory;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.repository.user.UserRepository;
import com.spring.clinicmedia.domain.result.UserCreationResult;
import com.spring.clinicmedia.domain.validator.EmailValidator;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import com.spring.clinicmedia.infrastructure.mapping.UserCreationResultMapping;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateAccount {

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    private final EmailValidator emailValidator;

    private final UserFactory userFactory;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserCreationResult execute(UserCreationCommand userCommand) {

        UserRepository<User> userRepository =
                userRepositoryDispatcher.getRepository(userCommand.getUserType());

        emailValidator.isValid(userCommand.getEmail());

        User userSaved = userFactory.create(userCommand);

        encodeUserPassword(userSaved, userCommand.getPassword());
        userRepository.saveUser(userSaved);

        return UserCreationResultMapping.mapToResult(userSaved);
    }

    private void encodeUserPassword(User user, String password) {
        user.getRegistration()
                .setPassword(passwordEncoder.encode(password));
    }

}
