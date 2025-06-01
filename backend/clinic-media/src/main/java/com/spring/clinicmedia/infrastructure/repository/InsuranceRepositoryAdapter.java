package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import com.spring.clinicmedia.infrastructure.Jpa.InsuranceJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class InsuranceRepositoryAdapter implements InsuranceRepository {


    private final InsuranceJpa insuranceJpa;

    @Override
    public Insurance save(Insurance entity) {
        return insuranceJpa.save(entity);
    }

    @Override
    public Insurance getById(String id) {
        return insuranceJpa.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException(id));
    }

    @Override
    public Optional<Insurance> findInsuranceByInsuranceNumber(String insuranceNumber) {
        return insuranceJpa.findById(insuranceNumber);
    }
}
