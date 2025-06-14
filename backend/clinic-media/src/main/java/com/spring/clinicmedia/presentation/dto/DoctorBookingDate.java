package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateResponse;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DoctorBookingDate {

    private BookingDateResponse bookingDateResponse;

    private String location;

}
