package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.user.ActivateUser;
import com.spring.clinicmedia.application.user.profile.ClinicProfileFetcher;
import com.spring.clinicmedia.application.user.profile.DoctorProfileFetcher;
import com.spring.clinicmedia.application.user.profile.InactiveAccountFetcher;
import com.spring.clinicmedia.application.user.profile.LabProfileFetcher;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.presentation.dto.admin.InactiveAccountResponse;
import com.spring.clinicmedia.presentation.dto.profile.ClinicProfile;
import com.spring.clinicmedia.presentation.dto.profile.DoctorProfile;
import com.spring.clinicmedia.presentation.dto.profile.LabProfile;
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

    private final DoctorProfileFetcher doctorProfileFetcher;

    private final LabProfileFetcher labProfileFetcher;

    private final ActivateUser activateUser;

    @GetMapping("/users/inactive-account")
    public ResponseEntity<List<InactiveAccountResponse>> getAccountNotActive(@RequestParam UserType userType,
                                                                             @RequestParam int pageNumber) {

        return ResponseEntity.ok(inactiveAccountFetcher.execute(userType, pageNumber));
    }

    @GetMapping("/clinic-profile/{clinicId}")
    public ResponseEntity<ClinicProfile> fitchClinicProfile(@PathVariable int clinicId) {
        return ResponseEntity.ok(clinicProfileFetcher.execute(clinicId));
    }

    @GetMapping("/doctor-profile/{doctorId}")
    public ResponseEntity<DoctorProfile> fitchDoctorProfile(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorProfileFetcher.execute(doctorId));
    }

    @GetMapping("/lab-profile/{labId}")
    public ResponseEntity<LabProfile> fitchLabProfile(@PathVariable int labId) {
        return ResponseEntity.ok(labProfileFetcher.execute(labId));
    }

    @PutMapping("/users/active/{userId}")
    public ResponseEntity<String> activateUser(@PathVariable int userId,
                                               @RequestParam UserType userType) {
        activateUser.execute(userId, userType);
        return ResponseEntity.ok("Successful Activated User");
    }

}
