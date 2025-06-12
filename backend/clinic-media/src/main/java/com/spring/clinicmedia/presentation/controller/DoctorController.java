package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.insurance.users.PatientAddInsurance;
import com.spring.clinicmedia.application.request.DoctorClinicRequestService;
import com.spring.clinicmedia.application.request.RequestFetcher;
import com.spring.clinicmedia.application.request.RequestStatusChangeHandler;
import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.presentation.dto.request.RequestChangeStatus;
import com.spring.clinicmedia.presentation.dto.request.RequestResponse;
import com.spring.clinicmedia.presentation.map.RequestResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@AllArgsConstructor
public class DoctorController {

    private final DoctorClinicRequestService doctorClinicRequestService;

    private final RequestFetcher requestFetcher;

    private final RequestStatusChangeHandler requestStatusChangeHandler;

    private final PatientAddInsurance patientAddInsurance;

    @PutMapping("/clinic/{clinicId}")
    public ResponseEntity<String> doctorsAddClinic(@PathVariable int clinicId, @AuthenticationPrincipal CustomUserDetail user) {

        doctorClinicRequestService.createRequest(user.getUserId(), clinicId, UserType.DOCTOR);

        return ResponseEntity.ok("Doctor added");
    }

    @GetMapping("/requests")
    public ResponseEntity<List<RequestResponse>> getDoctorReceivedRequests(@AuthenticationPrincipal CustomUserDetail user, @RequestParam int pageNumber) {

        List<ClinicDoctorRequest> clinicDoctorRequests = requestFetcher.execute(user.getUserId(), UserType.DOCTOR, pageNumber);
        return ResponseEntity.ok(RequestResponseMapper.createFromList(clinicDoctorRequests));
    }

    @PutMapping("/requests/{requestId}/status")
    public ResponseEntity<String> changeStataRequest(@AuthenticationPrincipal CustomUserDetail doctor,
                                                     @PathVariable long requestId,
                                                     @RequestBody RequestChangeStatus requestStatus) {

        requestStatusChangeHandler.execute(requestId,
                doctor.getUserId(),
                UserType.DOCTOR,
                requestStatus.getStatus());
        return ResponseEntity.ok("Request status changed");
    }

    @PutMapping("/insurance/{insuranceName}")
    public ResponseEntity<String> doctorAddInsurance(@PathVariable String insuranceName, @AuthenticationPrincipal CustomUserDetail user) {

        patientAddInsurance.addInsurance(insuranceName, user.getUserId());

        return ResponseEntity.ok("Doctor added");
    }


}
