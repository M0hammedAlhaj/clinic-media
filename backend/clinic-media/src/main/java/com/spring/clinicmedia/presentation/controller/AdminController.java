package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.clinic.ClinicProfileFetcher;
import com.spring.clinicmedia.application.doctor.DoctorProfileFetcher;
import com.spring.clinicmedia.application.insurance.InsuranceCreation;
import com.spring.clinicmedia.application.lab.LabProfileFetcher;
import com.spring.clinicmedia.application.user.activation.ActivateUser;
import com.spring.clinicmedia.application.user.activation.InactiveAccountFetcher;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.infrastructure.mapping.ClinicProfileMapper;
import com.spring.clinicmedia.infrastructure.mapping.DoctorProfileMapper;
import com.spring.clinicmedia.presentation.dto.InsuranceRequest;
import com.spring.clinicmedia.presentation.dto.admin.InactiveAccountResponse;
import com.spring.clinicmedia.presentation.dto.profile.ClinicProfile;
import com.spring.clinicmedia.presentation.dto.profile.DoctorProfile;
import com.spring.clinicmedia.presentation.dto.profile.LabProfile;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    private final InsuranceCreation insuranceCreation;

    @GetMapping("/users/inactive-account")
    public ResponseEntity<List<InactiveAccountResponse>> getAccountNotActive(@RequestParam UserType userType,
                                                                             @RequestParam int pageNumber) {

        return ResponseEntity.ok(inactiveAccountFetcher.execute(userType, pageNumber));
    }

    @GetMapping("/clinic-profile/{clinicId}")
    public ResponseEntity<ClinicProfile> fitchClinicProfile(@PathVariable int clinicId) {
        return ResponseEntity.ok(ClinicProfileMapper.toClinicProfile(clinicProfileFetcher.execute(clinicId)));
    }

    @GetMapping("/doctor-profile/{doctorId}")
    public ResponseEntity<DoctorProfile> fitchDoctorProfile(@PathVariable int doctorId) {

        return ResponseEntity.ok(DoctorProfileMapper.createFrom(doctorProfileFetcher.execute(doctorId)));
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

    @PostMapping("/insurances")
    public ResponseEntity<String> createInsurance(@RequestBody InsuranceRequest insurance) {
        String insuranceName =
                insuranceCreation.execute(insurance.getInsurance()).getInsuranceName();

        URI uriInsurance = URI.create(insuranceName);

        return ResponseEntity.created(uriInsurance).
                body("Insurance created with ID: " + insurance.getInsurance());
    }

}
