package com.spring.clinicmedia.model.user;


import com.spring.clinicmedia.model.BookingDate;
import com.spring.clinicmedia.model.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "DOCTORS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long doctorId;


    @ManyToMany(mappedBy = "doctors")
    private List<Clinic> clinics;

    @ManyToOne
    @JoinColumn(name = "speciality_name")
    private Speciality speciality;

    @OneToMany(mappedBy = "doctor")
    private List<BookingDate> bookingDates;

}
