package com.spring.clinicmedia.application.user.activation;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.UserRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ActivateUser {

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    @Transactional
    public void execute(long userId, UserType userType) {

        UserRepository<User> userRepository = userRepositoryDispatcher.getRepository(userType);

        userRepository.getUserByIdOrElseThrow(userId)
                .setActive(true);

    }


}
