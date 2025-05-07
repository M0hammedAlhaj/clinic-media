package com.spring.clinicmedia.domain.model.enitity;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class BookingDateId {

    private LocalDateTime bookingDate;

    private Long doctorId;
}
