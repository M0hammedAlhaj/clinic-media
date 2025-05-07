package com.spring.clinicmedia.domain.model.enitity;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BOOKING_DATES")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDate {

    @EmbeddedId
    private BookingDateId bookingDateId;

    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
