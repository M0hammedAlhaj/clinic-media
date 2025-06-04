package com.spring.clinicmedia.application.insurance;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import com.spring.clinicmedia.domain.port.repository.PatientRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserAddInsurance {

    private final UserRepositoryDispatcher userRepositoryDispatcher;

    private final PatientRepository patientRepository;

    private final ClinicRepository clinicRepository;

    private final InsuranceRepository insuranceRepository;

    public void execute(UserType userType,
                        String insuranceName
            , Long userId) {

        Insurance insurance =
                insuranceRepository.getById(insuranceName);

        if (userType.equals(UserType.PATIENT)) {
            Patient patient = patientRepository.getUserByIdOrElseThrow(userId);

            if (patient.getInsurances().isEmpty()) {
                patient.setInsurances(new ArrayList<>());
            }
            patient.getInsurances()
                    .add(insurance);
        }

        if (userType.equals(UserType.CLINIC)) {
            Clinic clinic = clinicRepository.getUserByIdOrElseThrow(userId);
            if (clinic.getInsurances().isEmpty()) {
                clinic.setInsurances(new ArrayList<>());
            }
            clinic.getInsurances()
                    .add(insurance);
        }
    }
}
