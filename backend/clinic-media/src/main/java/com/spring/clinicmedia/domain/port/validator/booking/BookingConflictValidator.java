package com.spring.clinicmedia.domain.port.validator.booking;

import java.time.LocalDateTime;

public interface BookingConflictValidator {

    void assertNoConflict(Long doctorId, LocalDateTime start, LocalDateTime end);
}
