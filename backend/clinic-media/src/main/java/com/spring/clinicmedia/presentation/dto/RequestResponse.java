package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse {

    private String clinicName;

    private RequestStatus status;

}
