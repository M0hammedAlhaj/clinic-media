package com.spring.clinicmedia.domain.validator.booking;

import java.time.LocalDateTime;

public interface BookingValidator {

    void validate(Long doctorId, Long clinicId,
                  LocalDateTime start, LocalDateTime end);
}
