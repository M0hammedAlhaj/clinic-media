package com.spring.clinicmedia.presentation.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PatientQuestionCreationResponse {

    private String hasDrugAllergy;

    private String hasChronicDisease;

    private String takesMedication;

    private String hasPreviousSurgeries;

}
