package com.spring.clinicmedia.infrastructure.validation.booking;

import com.spring.clinicmedia.domain.exception.DoctorNotInClinicException;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.validator.booking.DoctorInClinicValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DoctorInClinicValidatorAdapter implements DoctorInClinicValidator {

    private final DoctorRepository doctorRepository;

    @Override
    public void check(Long doctorId, Long clinicId) {
        if (!doctorRepository.isDoctorAssociatedWithClinic(doctorId, clinicId)) {
            throw new DoctorNotInClinicException(doctorId, clinicId);
        }
    }
}
