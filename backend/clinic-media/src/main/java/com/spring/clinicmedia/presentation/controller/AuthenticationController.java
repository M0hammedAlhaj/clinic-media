package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.user.registaration.CreateAccount;
import com.spring.clinicmedia.application.user.registaration.Login;
import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.result.UserCreationResult;
import com.spring.clinicmedia.domain.result.UserLoginResult;
import com.spring.clinicmedia.presentation.dto.createAccount.UserCreationRequest;
import com.spring.clinicmedia.presentation.dto.login.UserLoginRequest;
import com.spring.clinicmedia.presentation.map.UserCreationMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final CreateAccount createAccount;

    private final Login login;

    @PostMapping("/register")
    public ResponseEntity<UserCreationResult> creationResponse
            (@RequestBody @Valid UserCreationRequest request) {

        UserCreationCommand command = UserCreationMapper.toCommand(request);

        return ResponseEntity.ok(createAccount.execute(command));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> loginResponse(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(login.execute(request));
    }
}
