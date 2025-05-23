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
@RequestMapping("/api/v1/doctors")
@AllArgsConstructor
public class DoctorController {

    private final DoctorClinicRequestService doctorClinicRequestService;

    private final RequestFetcher requestFetcher;

    @PutMapping("/clinic/{clinicId}")
    public ResponseEntity<String> doctorsAddClinic(@PathVariable int clinicId,
                                                   @AuthenticationPrincipal CustomUserDetail user) {

        doctorClinicRequestService.createRequest(user.getUserId(), clinicId, UserType.DOCTOR);

        return ResponseEntity.ok("Doctor added");
    }

    @GetMapping("/requests")
    public ResponseEntity<List<RequestResponse>> getDoctorReceivedRequests(@AuthenticationPrincipal CustomUserDetail user
            , @RequestParam int pageNumber) {

        List<Request> requests = requestFetcher.execute(user.getUserId(),
                UserType.DOCTOR,
                pageNumber);
        return ResponseEntity.ok(RequestResponseMapper.createFromList(requests));
    }


}
