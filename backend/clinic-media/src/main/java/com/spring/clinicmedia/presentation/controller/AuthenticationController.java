package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.user.registaration.CreateAccount;
import com.spring.clinicmedia.application.user.registaration.Login;
import com.spring.clinicmedia.domain.command.UserCreationCommand;
import com.spring.clinicmedia.domain.result.UserCreationResult;
import com.spring.clinicmedia.domain.result.UserLoginResult;
import com.spring.clinicmedia.presentation.dto.createAccount.UserCreationRequest;
import com.spring.clinicmedia.presentation.dto.login.UserLoginRequest;
import com.spring.clinicmedia.presentation.map.UserCreationMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authentication")
@AllArgsConstructor
public class AuthenticationController {

    private final CreateAccount createAccount;
    private final Login login;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCreationResult.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<UserCreationResult> creationResponse(
            @RequestBody @Valid UserCreationRequest request) {

        UserCreationCommand command = UserCreationMapper.toCommand(request);
        return ResponseEntity.ok(createAccount.execute(command));
    }

    @Operation(summary = "Login an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginResult.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized / invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> loginResponse(
            @RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(login.execute(request));
    }
}
