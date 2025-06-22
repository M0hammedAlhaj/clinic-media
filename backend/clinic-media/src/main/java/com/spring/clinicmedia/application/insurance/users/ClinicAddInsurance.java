package com.spring.clinicmedia.application.insurance.users;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicAddInsurance implements UserInsuranceAdder {

    private final ClinicRepository clinicRepository;
    private final InsuranceRepository insuranceRepository;

    @Override
    @Transactional
    public User addInsurance(String insuranceName, Long userId) {

        Insurance insurance = insuranceRepository.getByIdOrElseThrow(insuranceName);
        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(userId);

        if (clinicRepository.isClinicHasInsurance(userId, insurance.getInsuranceName())) {
            throw new ResourceAlreadyExistsException(Clinic.class, insuranceName);
        }

        clinic.setInsurances(Optional.ofNullable(clinic.getInsurances())
                .orElseGet(ArrayList::new));

        clinic.getInsurances().add(insurance);

        return clinicRepository.saveUser(clinic);
    }
}
