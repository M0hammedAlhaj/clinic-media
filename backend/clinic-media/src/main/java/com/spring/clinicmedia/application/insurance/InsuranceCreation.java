package com.spring.clinicmedia.application.insurance;

import com.spring.clinicmedia.domain.exception.ResourcesAlreadyExists;
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
    public String execute(String name) {

        insuranceRepository.findInsuranceByInsuranceNumber(name).ifPresent((i) -> {
            throw new ResourcesAlreadyExists(Insurance.class, name);
        });

        return insuranceRepository.save(Insurance.builder()
                        .insuranceName(name)
                        .build())
                .getInsuranceName();
    }
}
