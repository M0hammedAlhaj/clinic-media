package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.ClinicsFetcher;
import com.spring.clinicmedia.application.DoctorBookingDateFetcher;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.CustomUserDetail;
import com.spring.clinicmedia.presentation.dto.ClinicResponse;
import com.spring.clinicmedia.presentation.dto.FilterSpecification;
import com.spring.clinicmedia.presentation.dto.PatientClinicView;
import com.spring.clinicmedia.presentation.map.ClinicResponseMapper;
import com.spring.clinicmedia.presentation.map.PatientClinicViewMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@AllArgsConstructor
public class PatientController {

    private final ClinicsFetcher clinicsFetcher;

    private final DoctorBookingDateFetcher doctorBookingDateFetcher;

    @GetMapping("/clinics")
    public ResponseEntity<List<ClinicResponse>> fitchClinics(@RequestParam(required = false) String insuranceName,
                                                             @RequestParam(required = false) String city,
                                                             @RequestParam int pageNumber,
                                                             @RequestParam int pageSize) {

        FilterSpecification filterSpecification = FilterSpecification.builder()
                .location(city)
                .nameInsurance(insuranceName)
                .build();


        return ResponseEntity.ok(ClinicResponseMapper.createFrom(
                clinicsFetcher.getClinics(pageNumber,
                        pageSize, filterSpecification)));
    }

    @GetMapping("/clinics/{clinicId}")
    public ResponseEntity<PatientClinicView> getClinic(@PathVariable long clinicId,
                                                       @AuthenticationPrincipal CustomUserDetail patient) {

        return ResponseEntity
                .ok(PatientClinicViewMapper
                        .createFrom(doctorBookingDateFetcher.execute(clinicId, BookingDateState.WAITING)));
    }

}
