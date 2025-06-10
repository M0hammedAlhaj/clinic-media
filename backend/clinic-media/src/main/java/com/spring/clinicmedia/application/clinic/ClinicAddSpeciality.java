package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.SpecialityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ClinicAddSpeciality {

    private final ClinicRepository clinicRepository;

    private final SpecialityRepository specialityRepository;

    @Transactional
    public Clinic addSpeciality(String specialityName, Long clinicId) {

        Speciality speciality = specialityRepository.getByIdOrElseThrow(specialityName);

        clinicRepository.findClinicByIdAndSpecialityName(clinicId, specialityName)
                .ifPresent(clinic -> {
                    throw new ResourceAlreadyExistsException(Clinic.class,
                            specialityName);
                });

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);

        if (clinic.getSpecialities().isEmpty()) {
            clinic.setSpecialities(new ArrayList<>());
        }
        clinic.getSpecialities()
                .add(speciality);

        return clinicRepository.saveUser(clinic);
    }
}
