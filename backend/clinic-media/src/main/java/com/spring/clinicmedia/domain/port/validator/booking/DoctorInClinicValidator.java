package com.spring.clinicmedia.domain.port.validator.booking;

public interface DoctorInClinicValidator {
    void check(Long doctorId, Long clinicId);
}
