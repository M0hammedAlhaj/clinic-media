package com.spring.clinicmedia.infrastructure.validation.booking;

import com.spring.clinicmedia.domain.port.validator.booking.BookingConflictValidator;
import com.spring.clinicmedia.domain.port.validator.booking.BookingValidator;
import com.spring.clinicmedia.domain.port.validator.booking.DateRangeValidator;
import com.spring.clinicmedia.domain.port.validator.booking.DoctorInClinicValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookingValidatorAdapter implements BookingValidator {

    private final BookingConflictValidator conflictValidator;

    private final DateRangeValidator dateRangeValidator;

    private final DoctorInClinicValidator doctorInClinicValidator;


    @Override
    public void validate(Long doctorId, Long clinicId, LocalDateTime start, LocalDateTime end) {
        conflictValidator.assertNoConflict(doctorId, start, end);
        dateRangeValidator.ensure(start, end);
        doctorInClinicValidator.check(doctorId, clinicId);
    }
}
