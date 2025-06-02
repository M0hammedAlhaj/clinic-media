package com.spring.clinicmedia.domain.port.validator.booking;

import java.time.LocalDateTime;

public interface DateRangeValidator {

    void ensure(LocalDateTime start, LocalDateTime end);
}
