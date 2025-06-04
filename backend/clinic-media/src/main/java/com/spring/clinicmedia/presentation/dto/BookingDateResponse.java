package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BookingDateResponse {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
