package com.spring.clinicmedia.domain.model.enitity.user;

import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.Location;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.notifications.NotificationClinic;
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
@AttributeOverride(
        name = "userId",
        column = @Column(name = "clinic_id")
)
public class Clinic extends User {


    @ManyToMany
    @JoinTable(name = "CLINCES_LOCATIONES",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Location> clinicLocations;

    @ManyToMany
    @JoinTable(name = "CLINCES_SPECIALITES",
            joinColumns = @JoinColumn(name = "speciality_name"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Speciality> specialities;


    @ManyToMany()
    @JoinTable(name = "CLINCES_INSURANCES",
            joinColumns = @JoinColumn(name = "insurance_name"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private List<Insurance> insurances;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "CLINCES_DOCTORS",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<NotificationClinic> notificationClinics;
}
