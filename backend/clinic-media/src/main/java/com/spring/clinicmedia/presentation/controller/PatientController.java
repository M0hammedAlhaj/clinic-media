package com.spring.clinicmedia.presentation.controller;

import com.spring.clinicmedia.application.ClinicsFetcher;
import com.spring.clinicmedia.presentation.dto.ClinicResponse;
import com.spring.clinicmedia.presentation.dto.FilterSpecification;
import com.spring.clinicmedia.presentation.map.ClinicResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@AllArgsConstructor
public class PatientController {

    private final ClinicsFetcher clinicsFetcher;


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

}
