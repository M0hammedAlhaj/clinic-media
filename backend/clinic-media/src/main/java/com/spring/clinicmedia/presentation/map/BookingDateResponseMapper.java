package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateResponse;

public class BookingDateResponseMapper {

    public static BookingDateResponse createFrom(BookingDate bookingDate) {
        return BookingDateResponse.builder()
                .id(bookingDate.getBookingDateId())
                .startTime(bookingDate.getBookingDateStarting())
                .endTime(bookingDate.getBookingDateEnding())
                .build();
    }

}
