package com.spring.clinicmedia.presentation.dto.bookingDate;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDateCreationRequest {

    private LocalDateTime startBookingDate;

    private LocalDateTime endBookingDate;

}
