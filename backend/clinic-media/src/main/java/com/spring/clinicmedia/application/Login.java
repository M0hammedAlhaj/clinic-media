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

        isPasswordMatch(loginRequest, registration);

        String token = generateToken.generateToken(loginRequest.getEmail());

        return getUserLoginResult(loginRequest, token);
    }

    private static UserLoginResult getUserLoginResult(UserLoginRequest loginRequest, String token) {
        return UserLoginResult.builder()
                .token(token)
                .userEmail(loginRequest.getEmail())
                .build();
    }

    private void isPasswordMatch(UserLoginRequest loginRequest, Registration registration) {
        if (!passwordEncoder.matches(loginRequest.getPassword(), registration.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
    }

}
