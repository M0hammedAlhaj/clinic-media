package com.spring.clinicmedia.infrastructure.validation.booking;

import com.spring.clinicmedia.domain.exception.booking.BookingConflictException;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.port.validator.booking.BookingConflictValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class BookingConflictValidatorAdapter implements BookingConflictValidator {

    private final BookingDateRepository bookingDateRepository;

    @Override
    public void assertNoConflict(Long doctorId, LocalDateTime start, LocalDateTime end) {
        if (bookingDateRepository
                .existsBookingDateByDoctorIdAndStartDateBetweenEndDate(doctorId, start, end)) {
            throw new BookingConflictException("There is already a booking in this date range for the doctor.");
        }
    }
}
