package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.SpecialityRepository;
import com.spring.clinicmedia.domain.port.validator.SpecialityValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ClinicAddSpeciality {

    private final ClinicRepository clinicRepository;

    private final SpecialityRepository specialityRepository;

    private final SpecialityValidator specialityValidator;

    @Transactional
    public Clinic addSpeciality(String specialityName, Long clinicId) {

        Speciality speciality = specialityRepository.getByIdOrElseThrow(specialityName);

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);

        specialityValidator.validateSpecialityAssignment(clinicId, specialityName);

        prepareClinicBeforeAddSpeciality(clinic);

        clinic.getSpecialities()
                .add(speciality);

        return clinicRepository.saveUser(clinic);
    }

    private static void prepareClinicBeforeAddSpeciality(Clinic clinic) {
        if (clinic.getSpecialities().isEmpty()) {
            clinic.setSpecialities(new ArrayList<>());
        }
    }
}
