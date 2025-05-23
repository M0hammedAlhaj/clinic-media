package com.spring.clinicmedia.domain.exception;

public class DoctorNotInClinicException extends RuntimeException {

    public DoctorNotInClinicException(Long doctorId, Long clinicId) {
        super("Doctor with ID " + doctorId + " is not assigned to clinic with ID " + clinicId);
    }
}
