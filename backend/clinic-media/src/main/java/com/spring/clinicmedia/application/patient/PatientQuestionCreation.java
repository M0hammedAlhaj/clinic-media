package com.spring.clinicmedia.application.patient;

import com.spring.clinicmedia.domain.model.enitity.PatientQuestion;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.repository.PatientQuestionRepository;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import com.spring.clinicmedia.presentation.dto.PatientQuestionCreationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientQuestionCreation {

    private final PatientQuestionRepository patientQuestionRepository;

    private final PatientRepository patientRepository;

    @Transactional
    public PatientQuestion create(Long patientId, PatientQuestionCreationResponse questionCreationResponse) {
        Patient patient = patientRepository.getUserByIdOrElseThrow(patientId);

        PatientQuestion patientQuestion = PatientQuestion.builder()
                .hasDrugAllergy(questionCreationResponse.getHasDrugAllergy())
                .takesMedication(questionCreationResponse.getTakesMedication())
                .hasChronicDisease(questionCreationResponse.getHasChronicDisease())
                .takesMedication(questionCreationResponse.getTakesMedication())
                .build();

        patient.setPatientQuestion(patientQuestion);

        return patientQuestionRepository.save(patientQuestion);

    }

}
