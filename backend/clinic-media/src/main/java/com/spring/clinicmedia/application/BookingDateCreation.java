package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.exception.DoctorNotInClinicException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import com.spring.clinicmedia.presentation.map.BookingDateCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookingDateCreation {

    private final BookingDateRepository bookingDateRepository;

    private final UserActivationValidator activationValidator;

    private final ClinicRepository clinicRepository;

    private final DoctorRepository doctorRepository;


    /*
    activate account
      clinic have a doctor

     */
    @Transactional
    public void execute(Long clinicId,
                        Long doctorId,
                        BookingDateCreationRequest bookingDateCreationRequest) {

        activationValidator.validate(clinicId, UserType.CLINIC);

        activationValidator.validate(doctorId, UserType.DOCTOR);

        Clinic clinic = clinicRepository.getUserById(clinicId);

        Doctor doctor = doctorRepository.getUserById(doctorId);

        if (!doctorRepository.existsDoctorInClinic(doctorId, clinicId)) {
            throw new DoctorNotInClinicException(doctorId, clinicId);
        }
    //check overlap
        BookingDate.builder()
    }

}
