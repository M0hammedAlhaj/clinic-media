package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.presentation.dto.request.RequestResponse;

import java.util.List;

public class RequestResponseMapper {


    public static RequestResponse createFrom(ClinicDoctorRequest clinicDoctorRequest) {
        return RequestResponse.builder()
                .requestId(clinicDoctorRequest.getRequestId())
                .clinicName(clinicDoctorRequest
                        .getClinic()
                        .getRegistration()
                        .getName())
                .status(clinicDoctorRequest.getStatus())
                .build();
    }

    public static List<RequestResponse> createFromList(List<ClinicDoctorRequest> clinicDoctorRequests) {
        return clinicDoctorRequests.stream()
                .map(RequestResponseMapper::createFrom)
                .toList();
    }
}
