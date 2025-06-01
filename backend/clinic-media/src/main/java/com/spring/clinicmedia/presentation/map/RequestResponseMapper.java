package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.presentation.dto.request.RequestResponse;

import java.util.List;

public class RequestResponseMapper {


    public static RequestResponse createFrom(Request request) {
        return RequestResponse.builder()
                .requestId(request.getRequestId())
                .clinicName(request
                        .getClinic()
                        .getRegistration()
                        .getName())
                .status(request.getStatus())
                .build();
    }

    public static List<RequestResponse> createFromList(List<Request> requests) {
        return requests.stream()
                .map(RequestResponseMapper::createFrom)
                .toList();
    }
}
