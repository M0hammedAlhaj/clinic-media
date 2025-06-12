package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.presentation.dto.BookAppointmentResponse;

public class BookAppointmentResponseMapper {

    public static BookAppointmentResponse createFron(BookingDate bookingDate) {

        return BookAppointmentResponse.builder()
                .bookingDateId(bookingDate.getBookingDateId())
                .clinicId(bookingDate.getClinic().getUserId())
                .doctorId(bookingDate.getDoctor().getUserId())
                .cityLocation(bookingDate.getLocation()
                        .getCountryName())
                .build();
    }
}
