package com.spring.clinicmedia.presentation.dto.request;

import com.spring.clinicmedia.domain.model.enitity.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestChangeStatus {

    private RequestStatus status;
}
