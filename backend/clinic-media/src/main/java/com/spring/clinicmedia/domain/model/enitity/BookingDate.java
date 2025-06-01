package com.spring.clinicmedia.domain.model.enitity;

import com.spring.clinicmedia.domain.model.BookingDateState;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "BOOKING_DATES")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDate {

    @Id
    @Column(name = "booking_data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingDateId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Column(name = "booking_date_starting")
    private LocalDateTime bookingDateStarting;

    @Column(name = "booking_date_ending")
    private LocalDateTime bookingDateEnding;

    @Column(name = "booking_date_status")
    @Enumerated(EnumType.STRING)
    private BookingDateState bookingDateStatus;
}
