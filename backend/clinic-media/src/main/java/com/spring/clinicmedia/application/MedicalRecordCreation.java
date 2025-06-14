package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.enitity.MedicalRecord;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.FileMalwareScanner;
import com.spring.clinicmedia.domain.port.SaveFile;
import com.spring.clinicmedia.domain.port.repository.MedicalRecordRepository;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import com.spring.clinicmedia.presentation.dto.MedicalRecordCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MedicalRecordCreation {

    private final FileMalwareScanner fileMalwareScanner;

    private final PatientRepository patientRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final SaveFile saveFile;

    @Transactional
    public MedicalRecord creation(MedicalRecordCreationRequest medicalRecordCreationRequest,
                                  Long patientId,
                                  MultipartFile medicalRecordFile) throws IOException {

        Patient patient = patientRepository.getUserByIdOrElseThrow(patientId);

        try {
            fileMalwareScanner.scan(medicalRecordFile.getBytes());
        } catch (IOException ioException) {
            throw new IOException("Medical record file could not be scanned.");
        }

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .medicalRecordType(medicalRecordCreationRequest.getMedicalRecordType())
                .description(medicalRecordCreationRequest.getDescription())
                .build();

        List<MedicalRecord> medicalRecordsPatient = Optional.ofNullable(patient.getMedicalRecord())
                .orElseGet(ArrayList::new);
        medicalRecordsPatient.add(medicalRecord);

        patient.setMedicalRecord(medicalRecordsPatient);


        /*
            Save File -> local / Server
         */

        String fileName = medicalRecord.getMedicalRecordId() + "_" +
                medicalRecordFile.getOriginalFilename();

        String url = saveFile.save(medicalRecordFile, fileName);
        medicalRecord.setUrl(url);

        medicalRecordRepository.save(medicalRecord);

        return medicalRecord;
    }
}
