package com.spring.clinicmedia.infrastructure.Jpa;

import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.Registration;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookingDateJpaTest {

    @Autowired
    private BookingDateJpa underTest;

    @Autowired
    private DoctorJpaRepository doctorJpaRepository;

    @Test
    void bookingCompletelyBefore_shouldNotOverlap() {
        Doctor doctor = saveTestDoctor();

        BookingDate existing = saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0), LocalDateTime.of(2025, 6, 10, 11, 0));
        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 9, 0),
                LocalDateTime.of(2025, 6, 10, 9, 59));

        assertThat(overlapping).isFalse();
    }

    @Test
    void bookingEndsExactlyWhenExistingStarts_shouldNotOverlap() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0), LocalDateTime.of(2025, 6, 10, 11, 0));
        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 9, 0),
                LocalDateTime.of(2025, 6, 10, 10, 0));

        assertThat(overlapping).isFalse();
    }

    @Test
    void bookingStartsExactlyWhenExistingEnds_shouldNotOverlap() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0),
                LocalDateTime.of(2025, 6, 10, 11, 0));
        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 11, 0),
                LocalDateTime.of(2025, 6, 10, 12, 0));

        assertThat(overlapping).isFalse();
    }

    @Test
    void bookingOverlapsStart_shouldBeTrue() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0),
                LocalDateTime.of(2025, 6, 10, 11, 0));

        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 9, 30),
                LocalDateTime.of(2025, 6, 10, 10, 30));

        assertThat(overlapping).isTrue();
    }

    @Test
    void bookingOverlapsEnd_shouldBeTrue() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0), LocalDateTime.of(2025, 6, 10, 11, 0));
        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 10, 30),
                LocalDateTime.of(2025, 6, 10, 11, 30));

        assertThat(overlapping).isTrue();
    }

    @Test
    void bookingInsideExisting_shouldBeTrue() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0),
                LocalDateTime.of(2025, 6, 10, 11, 0));
        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 10, 15),
                LocalDateTime.of(2025, 6, 10, 10, 45));

        assertThat(overlapping).isTrue();
    }

    @Test
    void bookingCoversExisting_shouldBeTrue() {
        Doctor doctor = saveTestDoctor();

        saveBooking(doctor, LocalDateTime.of(2025, 6, 10, 10, 0),
                LocalDateTime.of(2025, 6, 10, 11, 0));

        boolean overlapping = underTest.existsOverlappingBookingByDoctorId(doctor.getUserId(),
                LocalDateTime.of(2025, 6, 10, 9, 0),
                LocalDateTime.of(2025, 6, 10, 12, 0));

        assertThat(overlapping).isTrue();
    }

    private Doctor saveTestDoctor() {
        Registration registration = Registration.builder()
                .email("doctor@example.com")
                .name("Doctor")
                .password("pass")
                .build();
        Doctor doctor = new Doctor();
        doctor.setRegistration(registration);
        return doctorJpaRepository.save(doctor);
    }

    private BookingDate saveBooking(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        BookingDate booking = BookingDate.builder()
                .doctor(doctor)
                .bookingDateStarting(start)
                .bookingDateEnding(end)
                .build();
        return underTest.save(booking);
    }

}
