package com.spring.clinicmedia.presentation.dto.request;

import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse {

    private Long requestId;

    private String clinicName;

    private RequestStatus status;

}
