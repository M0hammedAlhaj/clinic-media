package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.MedicalRecord;
import com.spring.clinicmedia.domain.repository.MedicalRecordRepository;
import com.spring.clinicmedia.infrastructure.Jpa.MedicalRecordJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MedicalRecordRepositoryAdapter implements MedicalRecordRepository {

    private final MedicalRecordJpa repository;

    @Override
    public MedicalRecord save(MedicalRecord entity) {
        return repository.save(entity);
    }

    @Override
    public MedicalRecord getByIdOrElseThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourcesNotFoundException(MedicalRecord.class, id));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
