package com.spring.clinicmedia.application.booking;

import com.spring.clinicmedia.domain.exception.DoctorNotInClinicException;
import com.spring.clinicmedia.domain.exception.UserAccountNotActivation;
import com.spring.clinicmedia.domain.exception.booking.BookingConflictException;
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
import com.spring.clinicmedia.domain.port.validator.booking.BookingValidator;
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

    private final BookingValidator bookingValidator;

    /**
     * Creates a new booking date for a specific doctor and clinic based on the provided booking request.
     * <p>
     * The method performs the following steps:
     * <ul>
     *   <li>Validates that the clinic and doctor users are active.</li>
     *   <li>Retrieves the Clinic and Doctor entities by their IDs.</li>
     *   <li>Validates the booking date range and checks for conflicts using the BookingValidator.</li>
     *   <li>Builds a new BookingDate entity with status set to WAITING.</li>
     *   <li>Saves the booking date to the repository and returns the persisted entity.</li>
     * </ul>
     *
     * @param clinicId                   The ID of the clinic making the booking.
     * @param doctorId                   The ID of the doctor for whom the booking is made.
     * @param bookingDateCreationRequest The DTO containing start and end booking dates.
     * @return The persisted {@link BookingDate} entity representing the new booking.
     * @throws DoctorNotInClinicException  if the doctor doesn't belong to the clinic.
     * @throws InvalidBookingDateException if the start date is not before the end date.
     * @throws BookingConflictException    if there is a conflicting booking in the given date range.
     * @throws UserAccountNotActivation    if either the clinic or doctor user is not active.
     */
    @Transactional
    public BookingDate execute(Long clinicId,
                               Long doctorId,
                               BookingDateCreationRequest bookingDateCreationRequest) {

        activationValidator.validateUserIsActive(clinicId, UserType.CLINIC);

        activationValidator.validateUserIsActive(doctorId, UserType.DOCTOR);

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);

        Doctor doctor = doctorRepository.getUserByIdOrElseThrow(doctorId);

        LocalDateTime startBookingDate = bookingDateCreationRequest.
                getStartBookingDate();

        LocalDateTime endBookingDate = bookingDateCreationRequest
                .getEndBookingDate();

        bookingValidator.validate(doctorId, clinicId, startBookingDate, endBookingDate);


        BookingDate bookingDate = buildBookingDate(clinic, doctor, startBookingDate, endBookingDate);

        return bookingDateRepository.save(bookingDate);
    }



    private static BookingDate buildBookingDate(Clinic clinic,
                                                Doctor doctor,
                                                LocalDateTime startBookingDate,
                                                LocalDateTime endBookingDate) {
        return BookingDate.builder()
                .clinic(clinic)
                .doctor(doctor)
                .bookingDateStarting(startBookingDate)
                .bookingDateEnding(endBookingDate)
                .bookingDateStatus(BookingDateState.AWAITING_CONFIRMATION)
                .build();
    }

}
