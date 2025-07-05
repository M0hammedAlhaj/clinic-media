package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.enitity.MedicalRecord;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.files.FileMalwareScanner;
import com.spring.clinicmedia.domain.files.SaveFile;
import com.spring.clinicmedia.domain.repository.MedicalRecordRepository;
import com.spring.clinicmedia.domain.repository.user.PatientRepository;
import com.spring.clinicmedia.presentation.dto.MedicalRecordCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordCreation {

    private final FileMalwareScanner fileMalwareScanner;

    private final PatientRepository patientRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final SaveFile saveFile;

    @Transactional
    public void creation(MedicalRecordCreationRequest medicalRecordCreationRequest,
                         Long patientId) throws IOException {

        Patient patient = patientRepository.getUserByIdOrElseThrow(patientId);
        MultipartFile medicalRecordFile = medicalRecordCreationRequest.getFile();

        fileMalwareScanner.scan(medicalRecordFile.getBytes());


        MedicalRecord medicalRecord = buildMedicalRecord(medicalRecordCreationRequest);

        preparePatientAddMedicalRecord(patient);

        patient.getMedicalRecord().add(medicalRecord);


        String fileName = medicalRecord.getMedicalRecordId() + "_" +
                medicalRecordFile.getOriginalFilename();

        String url = saveFile.save(medicalRecordFile, fileName);

        medicalRecord.setUrl(url);

        medicalRecordRepository.save(medicalRecord);
    }

    private static void preparePatientAddMedicalRecord(Patient patient) {
        Optional.ofNullable(patient.getMedicalRecord())
                .orElseGet(ArrayList::new);
    }

    private static MedicalRecord buildMedicalRecord(MedicalRecordCreationRequest medicalRecordCreationRequest) {
        return MedicalRecord.builder()
                .medicalRecordType(medicalRecordCreationRequest.getMedicalRecordType())
                .description(medicalRecordCreationRequest.getDescription())
                .build();
    }
}
