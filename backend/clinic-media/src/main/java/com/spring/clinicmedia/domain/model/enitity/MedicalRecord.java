package com.spring.clinicmedia.domain.model.enitity;

import com.spring.clinicmedia.domain.model.MedicalRecordType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "MEDICAL_RECORDES")
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_record_id")
    private Long medicalRecordId;

    private String description;

    private String photo;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_record_type")
    private MedicalRecordType medicalRecordType;


}
