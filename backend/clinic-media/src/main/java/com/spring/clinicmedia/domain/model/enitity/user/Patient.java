package com.spring.clinicmedia.domain.model.enitity.user;

import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.Location;
import com.spring.clinicmedia.domain.model.enitity.MedicalRecord;
import com.spring.clinicmedia.domain.model.enitity.PatientQuestion;
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
@AttributeOverride(
        name="userId",
        column=@Column(name="patient_id")
)
public class Patient extends User {


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
