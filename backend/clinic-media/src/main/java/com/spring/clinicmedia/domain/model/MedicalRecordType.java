package com.spring.clinicmedia.domain.model;

import lombok.Getter;

@Getter
public enum MedicalRecordType {
    RECORD("General record"),
    DIAGNOSIS("Diagnosis report"),
    PRESCRIPTION("Prescription note"),
    LAB_RESULT("Lab test result"),
    IMAGING("Medical imaging result"),
    VACCINATION("Vaccination record");


    private final String description;

    MedicalRecordType(String description) {
        this.description = description;
    }

}

