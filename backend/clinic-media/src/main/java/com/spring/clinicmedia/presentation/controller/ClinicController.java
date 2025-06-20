package com.spring.clinicmedia.presentation.controller;


import com.spring.clinicmedia.application.booking.BookingDateCreation;
import com.spring.clinicmedia.application.clinic.ClinicSpecialityAssignmentService;
import com.spring.clinicmedia.application.insurance.users.ClinicAddInsurance;
import com.spring.clinicmedia.application.request.DoctorClinicRequestService;
import com.spring.clinicmedia.application.request.RequestFetcher;
import com.spring.clinicmedia.application.request.RequestStatusChangeHandler;
import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.presentation.dto.ClinicResponse;
import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateCreationRequest;
import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateCreationResponse;
import com.spring.clinicmedia.presentation.dto.request.RequestChangeStatus;
import com.spring.clinicmedia.presentation.dto.request.RequestResponse;
import com.spring.clinicmedia.presentation.map.BookingDateCreationResponseMapper;
import com.spring.clinicmedia.presentation.map.ClinicResponseMapper;
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

    private final BookingDateCreation bookingDateCreation;

    private final RequestStatusChangeHandler requestStatusChangeHandler;

    private final ClinicAddInsurance clinicAddInsurance;

    private final ClinicSpecialityAssignmentService clinicSpecialityAssignmentService;

    @PutMapping("/doctor/{doctorId}")
    public ResponseEntity<String> doctorsAddClinic(@PathVariable int doctorId,
                                                   @AuthenticationPrincipal CustomUserDetail user) {

        doctorClinicRequestService.createRequest(doctorId, user.getUserId(), UserType.CLINIC);

        return ResponseEntity.ok("Doctor added");
    }

    @GetMapping("/requests")
    public ResponseEntity<List<RequestResponse>> getClinicReceivedRequest(@AuthenticationPrincipal CustomUserDetail user
            , @RequestParam int pageNumber) {

        List<ClinicDoctorRequest> clinicDoctorRequests =
                requestFetcher.execute(user.getUserId(), UserType.CLINIC, pageNumber);
        return ResponseEntity.ok(RequestResponseMapper.createFromList(clinicDoctorRequests));
    }

    @PutMapping("/bookingDates/{doctorId}")
    public ResponseEntity<BookingDateCreationResponse> addBookingsDate(@AuthenticationPrincipal CustomUserDetail clinic,
                                                                       @RequestBody BookingDateCreationRequest bookingDateCreationRequest,
                                                                       @PathVariable long doctorId) {

        BookingDate bookingDate = bookingDateCreation.execute(clinic.getUserId(),
                doctorId,
                bookingDateCreationRequest);

        return ResponseEntity.ok(BookingDateCreationResponseMapper.createFrom(bookingDate));
    }

    @PutMapping("/requests/{requestId}/status")
    public ResponseEntity<String> changeStataRequest(@AuthenticationPrincipal CustomUserDetail clinic,
                                                     @PathVariable long requestId,
                                                     @RequestBody RequestChangeStatus requestStatus) {

        requestStatusChangeHandler.execute(requestId,
                clinic.getUserId(),
                UserType.CLINIC,
                requestStatus.getStatus());

        return ResponseEntity.ok("Request status changed");
    }

    @PutMapping("/insurance/{insuranceName}")
    public ResponseEntity<String> clinicAddInsurance
            (@AuthenticationPrincipal CustomUserDetail clinic,
             @PathVariable String insuranceName) {

        clinicAddInsurance.addInsurance(insuranceName, clinic.getUserId());

        return ResponseEntity.ok("Add Insurance");
    }

    @PutMapping("/speciality/{specialityName}")
    public ResponseEntity<ClinicResponse> clinicAddSpecie(
            @AuthenticationPrincipal CustomUserDetail clinic,
            @PathVariable String specialityName) {

        return ResponseEntity.ok(
                ClinicResponseMapper.createFrom(clinicSpecialityAssignmentService.addSpeciality(specialityName, clinic.getUserId())));

    }
}
