package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientBookingManager {

    private final PatientRepository patientRepository;

    private final BookingDateRepository bookingDateRepository;

    @Transactional
    public BookingDate bookAppointment(Long patientId, Long bookingId) {

        Patient patient = patientRepository.getUserByIdOrElseThrow(patientId);

        BookingDate bookingDate = bookingDateRepository.getByIdOrElseThrow(bookingId);

        if (bookingDate.getPatient() != null) {
            throw new InvalidBookingDateException("Booking date is already reserved.");
        }
        bookingDate.setPatient(patient);
        bookingDate.setBookingDateStatus(BookingDateState.UPCOMING);

        return bookingDateRepository.save(bookingDate);
    }
}
