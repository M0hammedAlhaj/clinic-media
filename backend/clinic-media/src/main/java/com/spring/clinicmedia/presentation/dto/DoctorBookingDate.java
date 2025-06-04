package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorBookingDate {

    private BookingDateResponse bookingDateResponse;

    private String location;

}
