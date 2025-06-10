package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.exception.UserAccountNotActivation;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.SpecialityRepository;
import com.spring.clinicmedia.domain.port.validator.SpecialityValidator;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ClinicSpecialityAssignmentServiceTest {

    private ClinicRepository clinicRepository;
    private SpecialityRepository specialityRepository;
    private SpecialityValidator specialityValidator;
    private UserActivationValidator activationValidator;
    private ClinicSpecialityAssignmentService underTest;

    @BeforeEach
    void setUp() {
        clinicRepository = mock(ClinicRepository.class);
        specialityRepository = mock(SpecialityRepository.class);
        specialityValidator = mock(SpecialityValidator.class);
        activationValidator = mock(UserActivationValidator.class);
        underTest = new ClinicSpecialityAssignmentService(
                clinicRepository,
                specialityRepository,
                specialityValidator,
                activationValidator
        );
    }

    @Test
    void addSpeciality_addsSpecialitySuccessfully() {
        // Given
        Long clinicId = 1L;
        String specialityName = "Cardiology";
        Clinic clinic = new Clinic();
        clinic.setSpecialities(Collections.emptyList());
        Speciality speciality = new Speciality();

        when(specialityRepository.getByIdOrElseThrow(specialityName)).thenReturn(speciality);
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(clinic);
        when(clinicRepository.saveUser(clinic)).thenReturn(clinic);

        // When
        Clinic result = underTest.addSpeciality(specialityName, clinicId);

        // Then
        assertThat(result.getSpecialities()).contains(speciality);

        verify(specialityRepository).getByIdOrElseThrow(specialityName);
        verify(clinicRepository).getUserByIdOrElseThrow(clinicId);
        verify(activationValidator).validate(clinicId, UserType.CLINIC);
        verify(specialityValidator).validateSpecialityAssignment(clinicId, specialityName);
        verify(clinicRepository).saveUser(clinic);
    }

    @Test
    void addSpeciality_throwsException_whenSpecialityAlreadyExists() {
        // Given
        Long clinicId = 1L;
        String specialityName = "Cardiology";

        when(specialityRepository.getByIdOrElseThrow(specialityName)).thenReturn(new Speciality());
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(new Clinic());

        doThrow(new ResourceAlreadyExistsException(Clinic.class, specialityName))
                .when(specialityValidator)
                .validateSpecialityAssignment(clinicId, specialityName);

        // When / Then
        assertThatThrownBy(() -> underTest.addSpeciality(specialityName, clinicId))
                .isInstanceOf(ResourceAlreadyExistsException.class);

        verify(specialityValidator).validateSpecialityAssignment(clinicId, specialityName);
    }

    @Test
    void addSpeciality_throwsException_whenClinicNotFound() {
        // Given
        Long clinicId = 1L;
        String specialityName = "Cardiology";

        when(specialityRepository.getByIdOrElseThrow(specialityName)).thenReturn(new Speciality());
        when(clinicRepository.getUserByIdOrElseThrow(clinicId))
                .thenThrow(new ResourcesNotFoundException(Clinic.class, clinicId));

        // When / Then
        assertThatThrownBy(() -> underTest.addSpeciality(specialityName, clinicId))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(clinicRepository).getUserByIdOrElseThrow(clinicId);
    }

    @Test
    void addSpeciality_throwsException_whenClinicIsNotActivated() {
        // Given
        Long clinicId = 1L;
        String specialityName = "Cardiology";

        when(specialityRepository.getByIdOrElseThrow(specialityName)).thenReturn(new Speciality());
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(new Clinic());

        doThrow(new UserAccountNotActivation(Clinic.class, clinicId))
                .when(activationValidator)
                .validate(clinicId, UserType.CLINIC);

        // When / Then
        assertThatThrownBy(() -> underTest.addSpeciality(specialityName, clinicId))
                .isInstanceOf(UserAccountNotActivation.class);

        verify(activationValidator).validate(clinicId, UserType.CLINIC);
    }
}
