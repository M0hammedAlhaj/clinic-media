package com.spring.clinicmedia.presentation.dto.bookingDate;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDateCreationResponse {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String clinicName;

    private String DoctorName;

    private String status;

}
