package com.spring.clinicmedia.application.user.activation;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.UserRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import com.spring.clinicmedia.presentation.dto.admin.InactiveAccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class InactiveAccountFetcher {

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    public List<InactiveAccountResponse> execute(UserType type, int pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, 10);

        UserRepository<User> userRepository =
                userRepositoryDispatcher.getRepository(type);

        return userRepository.
                getUserByActive(false, pageable)
                .getContent()
                .stream()
                .map(createUserInactiveAccountResponseFunction())
                .toList();
    }

    private static Function<User, InactiveAccountResponse> createUserInactiveAccountResponseFunction() {
        return user -> InactiveAccountResponse
                .builder()
                .userId(user.getUserId())
                .userEmail(user.getRegistration().getEmail())
                .build();
    }

}
