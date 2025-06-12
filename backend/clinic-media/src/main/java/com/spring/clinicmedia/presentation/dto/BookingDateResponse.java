package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BookingDateResponse {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
