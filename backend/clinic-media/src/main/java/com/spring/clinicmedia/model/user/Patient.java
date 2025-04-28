package com.spring.clinicmedia.model.user;

import com.spring.clinicmedia.model.Insurance;
import com.spring.clinicmedia.model.Location;
import com.spring.clinicmedia.model.MedicalRecord;
import com.spring.clinicmedia.model.PatientQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PATIENTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long clinicId;


    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "PATIENTS_INSURANCES",
            joinColumns = @JoinColumn(name = "insurance_name"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Insurance> insurances;

    @OneToMany
    @JoinColumn(name = "medical_record_id")
    private List<MedicalRecord> medicalRecord;

    @OneToOne
    @JoinColumn(name = "patient_question_id")
    private PatientQuestion patientQuestion;
}
