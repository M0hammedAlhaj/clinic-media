package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookAppointmentResponse {

    private Long bookingDateId;

    private Long doctorId;

    private Long clinicId;

    private String cityLocation;


}
