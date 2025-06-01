package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateCreationResponse;

public class BookingDateCreationResponseMapper {

    public static BookingDateCreationResponse createFrom(BookingDate bookingDate) {
        return BookingDateCreationResponse.builder()
                .clinicName(bookingDate.getClinic().getRegistration().getName())
                .DoctorName(bookingDate.getDoctor().getRegistration().getName())
                .startTime(bookingDate.getBookingDateStarting())
                .endTime(bookingDate.getBookingDateEnding())
                .build();
    }
}
