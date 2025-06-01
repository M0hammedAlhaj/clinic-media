package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.exception.booking.BookingConflictException;
import com.spring.clinicmedia.domain.exception.DoctorNotInClinicException;
import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import com.spring.clinicmedia.presentation.dto.bookingDate.BookingDateCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookingDateCreation {

    private final BookingDateRepository bookingDateRepository;

    private final UserActivationValidator activationValidator;

    private final ClinicRepository clinicRepository;

    private final DoctorRepository doctorRepository;


    @Transactional
    public BookingDate execute(Long clinicId,
                               Long doctorId,
                               BookingDateCreationRequest bookingDateCreationRequest) {

        activationValidator.validate(clinicId, UserType.CLINIC);

        activationValidator.validate(doctorId, UserType.DOCTOR);

        Clinic clinic = clinicRepository.getUserById(clinicId);

        Doctor doctor = doctorRepository.getUserById(doctorId);

        LocalDateTime startBookingDate = bookingDateCreationRequest.
                getStartBookingDate();

        LocalDateTime endBookingDate = bookingDateCreationRequest
                .getEndBookingDate();

        if (!doctorRepository.existsDoctorInClinic(doctorId, clinicId)) {
            throw new DoctorNotInClinicException(doctorId, clinicId);
        }

        if (!startBookingDate
                .isBefore(endBookingDate)) {
            throw new InvalidBookingDateException("Start date must be before end date.");

        }

        if (bookingDateRepository.
                existsBookingDateByDoctorIdAndStartDateBetweenEndDate
                        (doctorId, startBookingDate, endBookingDate)) {
            throw new BookingConflictException("There is already a booking in this date range for the doctor.");
        }

        BookingDate bookingDate = BookingDate.builder()
                .clinic(clinic)
                .doctor(doctor)
                .bookingDateStarting(startBookingDate)
                .bookingDateEnding(endBookingDate)
                .bookingDateStatus(BookingDateState.WAITING)
                .build();

        return bookingDateRepository.save(bookingDate);
    }

}
