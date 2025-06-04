package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;

@Builder
@Data
public class BookingDateDoctor {


    private LocalDateTime clinicName;

    private HashMap<String, BookingDateResponse> doctorBookingDate;

    private String location;

}
