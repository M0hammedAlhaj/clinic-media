package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.port.jwt.GenerateToken;
import com.spring.clinicmedia.domain.port.repository.RegistrationRepository;
import com.spring.clinicmedia.domain.result.UserLoginResult;
import com.spring.clinicmedia.presentation.dto.login.UserLoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Login {

    private final GenerateToken generateToken;

    private final RegistrationRepository registrationRepository;

    private final PasswordEncoder passwordEncoder;

    public UserLoginResult execute(UserLoginRequest loginRequest) {

        Registration registration = registrationRepository.getByEmail(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), registration.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = generateToken.generateToken(loginRequest.getEmail());

        return UserLoginResult.builder()
                .token(token)
                .userEmail(loginRequest.getEmail())
                .build();
    }
}
