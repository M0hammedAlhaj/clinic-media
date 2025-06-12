package com.spring.clinicmedia.application.insurance;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InsuranceCreation {

    private final InsuranceRepository insuranceRepository;

    @Transactional
    public Insurance execute(String name) {

        insuranceRepository.findInsuranceByInsuranceNumber(name)
                .ifPresent((i) -> {
                    throw new ResourceAlreadyExistsException(Insurance.class, name);
                });

        return insuranceRepository.save(buildInsurance(name));
    }

    private Insurance buildInsurance(String name) {
        return Insurance.builder()
                .insuranceName(name)
                .build();
    }
}
