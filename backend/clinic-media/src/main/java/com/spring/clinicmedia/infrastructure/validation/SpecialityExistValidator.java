package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.repository.user.ClinicRepository;
import com.spring.clinicmedia.domain.validator.SpecialityValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Validator that ensures a speciality is not already assigned to a clinic.
 * <p>
 * This validator checks if a given speciality is already associated with a clinic
 * using the clinic's ID and the speciality name. If the association exists,
 * a {@link ResourceAlreadyExistsException} is thrown to prevent duplication.
 */
@Component
@AllArgsConstructor
public class SpecialityExistValidator implements SpecialityValidator {

    private final ClinicRepository clinicRepository;

    /**
     * Validates that the given speciality is not already assigned to the clinic.
     *
     * @param userId         the ID of the clinic (treated as userId in the context)
     * @param specialityName the name of the speciality to validate
     * @throws ResourceAlreadyExistsException if the clinic already has the given speciality
     */
    @Override
    public void validateSpecialityAssignment(Long userId, String specialityName) {
        clinicRepository.findClinicByIdAndSpecialityName(userId, specialityName)
                .ifPresent(c -> {
                    throw new ResourceAlreadyExistsException(
                            Clinic.class,
                            specialityName
                    );
                });
    }
}
