package com.spring.clinicmedia.domain.model.enitity.user;


import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
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
@AttributeOverride(
        name = "userId",
        column = @Column(name = "doctor_id")
)
public class Doctor extends User {


    @ManyToMany(mappedBy = "doctors",cascade = CascadeType.MERGE)
    private List<Clinic> clinics;

    @ManyToOne
    @JoinColumn(name = "speciality_name")
    private Speciality speciality;

    @OneToMany(mappedBy = "doctor")
    private List<BookingDate> bookingDates;

}
