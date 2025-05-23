package com.spring.clinicmedia.presentation.map;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDateCreationRequest {

    private LocalDateTime startBookingDate;

    private LocalDateTime endBookingDate;


}
