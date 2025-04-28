package com.spring.clinicmedia.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class BookingDateId {

    private LocalDateTime bookingDate;

    private Long doctorId;
}
