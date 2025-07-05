package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.repository.user.ClinicRepository;
import com.spring.clinicmedia.domain.repository.SpecialityRepository;
import com.spring.clinicmedia.domain.validator.SpecialityValidator;
import com.spring.clinicmedia.domain.validator.UserActivationValidator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

/**
 * Service for assigning a speciality to a clinic.
 */
@Service
@AllArgsConstructor
@Validated
public class ClinicSpecialityAssignmentService {

    private final ClinicRepository clinicRepository;
    private final SpecialityRepository specialityRepository;
    private final SpecialityValidator specialityValidator;
    private final UserActivationValidator activationValidator;

    /**
     * Assigns a speciality to a clinic after validation.
     *
     * @param specialityName the name of the speciality
     * @param clinicId       the ID of the clinic
     * @return the updated clinic with the new speciality
     */
    @Transactional
    public Clinic addSpeciality(@NotNull String specialityName, @NotNull Long clinicId) {

        Speciality speciality = specialityRepository.getByIdOrElseThrow(specialityName);

        Clinic clinic = clinicRepository.getUserByIdOrElseThrow(clinicId);

        activationValidator.validateUserIsActive(clinicId, UserType.CLINIC);

        specialityValidator.validateSpecialityAssignment(clinicId, specialityName);

        prepareClinicBeforeAddSpeciality(clinic);

        clinic.getSpecialities().add(speciality);
        return clinicRepository.saveUser(clinic);
    }

    private static void prepareClinicBeforeAddSpeciality(Clinic clinic) {
        if (clinic.getSpecialities() == null || clinic.getSpecialities().isEmpty()) {
            clinic.setSpecialities(new ArrayList<>());
        }
    }
}
