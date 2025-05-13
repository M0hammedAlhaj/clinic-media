package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.RequestCreationService;
import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.model.UserType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctors")
@AllArgsConstructor
public class DoctorController {

    private final RequestCreationService requestCreationService;

    @PutMapping("/clinic/{clinicId}")
    public ResponseEntity<String> doctorsAddClinic(@PathVariable int clinicId,
                                           @AuthenticationPrincipal CustomUserDetail user) {
        requestCreationService.createRequest(user.getUserId(), clinicId, UserType.DOCTOR);

        return ResponseEntity.ok("Doctor added");
    }
}
