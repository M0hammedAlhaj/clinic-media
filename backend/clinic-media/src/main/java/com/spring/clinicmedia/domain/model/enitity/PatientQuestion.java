package com.spring.clinicmedia.domain.model.enitity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "PATIENT_QUESTIONES")
@Builder
public class    PatientQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_question_id")
    private Long patientQuestionId;

    @Column(name = "has_drug_allergy")
    private String hasDrugAllergy;

    @Column(name = "has_chronic_disease")
    private String hasChronicDisease;

    @Column(name = "takes_medication")
    private String takesMedication;

    @Column(name = "has_previous_surgeries")
    private String hasPreviousSurgeries;
}
