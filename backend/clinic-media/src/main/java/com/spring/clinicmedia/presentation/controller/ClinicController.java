package com.spring.clinicmedia.presentation.controller;


import com.spring.clinicmedia.application.DoctorClinicRequestService;
import com.spring.clinicmedia.application.RequestFetcher;
import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.presentation.dto.RequestResponse;
import com.spring.clinicmedia.presentation.map.RequestResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
@AllArgsConstructor
public class ClinicController {

    private final DoctorClinicRequestService doctorClinicRequestService;

    private final RequestFetcher requestFetcher;

    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<String> doctorsAddClinic(@PathVariable int doctorId,
                                                   @AuthenticationPrincipal CustomUserDetail user) {

        doctorClinicRequestService.createRequest(doctorId, user.getUserId(), UserType.CLINIC);

        return ResponseEntity.ok("Doctor added");
    }


    @GetMapping("/requests")
    public ResponseEntity<List<RequestResponse>> getClinicReceivedRequest(@AuthenticationPrincipal CustomUserDetail user
            , @RequestParam int pageNumber) {
        List<Request> requests = requestFetcher.execute(user.getUserId(), UserType.CLINIC, pageNumber);
        return ResponseEntity.ok(RequestResponseMapper.createFromList(requests));
    }


}
