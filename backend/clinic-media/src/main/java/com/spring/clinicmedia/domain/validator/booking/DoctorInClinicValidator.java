package com.spring.clinicmedia.domain.validator.booking;

public interface DoctorInClinicValidator {
    void check(Long doctorId, Long clinicId);
}
