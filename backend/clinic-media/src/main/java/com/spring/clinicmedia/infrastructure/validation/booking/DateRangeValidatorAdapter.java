package com.spring.clinicmedia.infrastructure.validation.booking;

import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.domain.port.validator.booking.DateRangeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DateRangeValidatorAdapter implements DateRangeValidator {
    @Override
    public void ensure(LocalDateTime start, LocalDateTime end) {

        if (!start.isBefore(end)) {
            throw new InvalidBookingDateException("Start date must be before end date.");
        }
    }
}
