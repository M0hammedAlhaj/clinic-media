package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.InactiveAccountFetcher;
import com.spring.clinicmedia.application.ClinicProfileFetcher;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.presentation.dto.ClinicProfile;
import com.spring.clinicmedia.presentation.dto.DoctorProfile;
import com.spring.clinicmedia.presentation.dto.admin.InactiveAccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private final InactiveAccountFetcher inactiveAccountFetcher;

    private final ClinicProfileFetcher clinicProfileFetcher;

    @GetMapping("/users/inactive-account")
    public ResponseEntity<List<InactiveAccountResponse>> getAccountNotActive(@RequestParam UserType userType,
                                                                             @RequestParam int pageNumber) {

        return ResponseEntity.ok(inactiveAccountFetcher.execute(userType, pageNumber));
    }

    @GetMapping("/clinic-profile/{clinicId}")
    public ResponseEntity<ClinicProfile> userProfile(@PathVariable int clinicId) {
        return ResponseEntity.ok(clinicProfileFetcher.execute(clinicId));
    }

    @GetMapping("/doctor-profile/{doctorId}")
    public ResponseEntity<DoctorProfile>
}
