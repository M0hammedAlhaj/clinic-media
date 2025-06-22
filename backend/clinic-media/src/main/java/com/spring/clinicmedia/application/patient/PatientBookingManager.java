package com.spring.clinicmedia.application.patient;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.exception.booking.InvalidBookingDateException;
import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.repository.BookingDateRepository;
import com.spring.clinicmedia.domain.repository.user.PatientRepository;
import com.spring.clinicmedia.infrastructure.notifications.NotificationProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientBookingManager {

    private final PatientRepository patientRepository;

    private final BookingDateRepository bookingDateRepository;

    private final NotificationProcessor notificationProcessor;

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

        notificationProcessor.createNotification(creationCommand(UserType.DOCTOR,
                "Mock Message"
                , bookingDate.getDoctor()));

        notificationProcessor.createNotification(creationCommand(UserType.CLINIC,
                "Mock Message",
                bookingDate.getClinic()));

        return savedBooking;
    }

    NotificationCreationCommand<User> creationCommand(UserType receiverType,
                                                      String message,
                                                      User user) {

        return NotificationCreationCommand.builder()
                .message(message)
                .receiverType(receiverType)
                .user(user)
                .build();
    }
}
