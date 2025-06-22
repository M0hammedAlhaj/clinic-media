package com.spring.clinicmedia.application.patient;

import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.NotificationSender;
import com.spring.clinicmedia.domain.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.repository.user.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientBookingManager {

    private final PatientRepository patientRepository;

    private final BookingDateRepository bookingDateRepository;

    private final NotificationSender notificationSender;

    /*
    SEND FOR CLINIC AND DOCTOR ->
     */
    @Transactional
    public BookingDate bookAppointment(Long patientId, Long bookingId) {

        Patient patient = patientRepository.getUserByIdOrElseThrow(patientId);

        BookingDate bookingDate = bookingDateRepository.getByIdOrElseThrow(bookingId);

        if (bookingDate.getPatient() != null) {
            throw new InvalidBookingDateException("Booking date is already reserved.");
        }
        bookingDate.setPatient(patient);
        bookingDate.setBookingDateStatus(BookingDateState.UPCOMING);


        BookingDate savedBooking = bookingDateRepository.save(bookingDate);

        notificationSender.notify("", bookingDate.getDoctor().getRegistration().getName());
        notificationSender.notify("", bookingDate.getClinic().getRegistration().getName());
        return savedBooking;
    }
}
