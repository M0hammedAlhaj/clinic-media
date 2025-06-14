package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.domain.model.MedicalRecordType;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class MedicalRecordCreationRequest {

    private String description;

    private MedicalRecordType medicalRecordType;

    private MultipartFile file;

}
