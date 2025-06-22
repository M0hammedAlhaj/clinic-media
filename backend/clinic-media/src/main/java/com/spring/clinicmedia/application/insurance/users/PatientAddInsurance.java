package com.spring.clinicmedia.application.insurance.users;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import com.spring.clinicmedia.domain.port.repository.user.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PatientAddInsurance implements UserInsuranceAdder {

    private final PatientRepository patientRepository;
    private final InsuranceRepository insuranceRepository;


    @Override
    @Transactional
    public User addInsurance(String insuranceName, Long userId) {

        Insurance insurance =
                insuranceRepository.getByIdOrElseThrow(insuranceName);

        Patient patient = patientRepository.getUserByIdOrElseThrow(userId);


        if (patientRepository.isPatientHasInsurance(userId, insuranceName))
            throw new ResourceAlreadyExistsException(Patient.class, insuranceName);

        patient.setInsurances(
                Optional.ofNullable(patient.getInsurances())
                        .orElseGet(ArrayList::new)
        );

        patient.getInsurances().add(insurance);


        return patientRepository.saveUser(patient);
    }

}
