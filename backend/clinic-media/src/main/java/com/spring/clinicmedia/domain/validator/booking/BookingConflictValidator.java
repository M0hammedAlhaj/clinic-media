package com.spring.clinicmedia.domain.validator.booking;

import java.time.LocalDateTime;

public interface BookingConflictValidator {

    void assertNoConflict(Long doctorId, LocalDateTime start, LocalDateTime end);
}
