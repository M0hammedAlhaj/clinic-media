package com.spring.clinicmedia.model.user;

import com.spring.clinicmedia.model.Insurance;
import com.spring.clinicmedia.model.Location;
import com.spring.clinicmedia.model.Speciality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "CLINICS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Clinic extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private Long clinicId;


    @ManyToMany
    @JoinTable(name = "CLINCES_LOCATIONES",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Location> clincLocations;

    @ManyToMany
    @JoinTable(name = "CLINCES_SPECIALITES",
            joinColumns = @JoinColumn(name = "speciality_name"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Speciality> specialities;


    @ManyToMany
    @JoinTable(name = "CLINCES_INSURANCES",
            joinColumns = @JoinColumn(name = "insurance_name"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Insurance> insurances;

    @ManyToMany
    @JoinTable(name = "CLINCES_DOCTORS",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Doctor> doctors;
}
