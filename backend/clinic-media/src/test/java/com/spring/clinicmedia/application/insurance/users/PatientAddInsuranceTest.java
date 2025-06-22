package com.spring.clinicmedia.application.insurance.users;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.domain.repository.InsuranceRepository;
import com.spring.clinicmedia.domain.repository.user.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class PatientAddInsuranceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private PatientAddInsurance patientAddInsurance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addInsurance_ShouldAddInsuranceSuccessfully_WhenPatientDoesNotHaveIt() {
        // Arrange
        Long userId = 1L;
        String insuranceName = "AXA";

        Insurance insurance = new Insurance();
        insurance.setInsuranceName(insuranceName);

        Patient patient = new Patient();
        patient.setUserId(userId);
        patient.setInsurances(null);

        when(patientRepository.getUserByIdOrElseThrow(userId)).thenReturn(patient);
        when(insuranceRepository.getByIdOrElseThrow(insuranceName)).thenReturn(insurance);
        when(patientRepository.isPatientHasInsurance(userId, insuranceName)).thenReturn(false);
        when(patientRepository.saveUser(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Patient result = (Patient) patientAddInsurance.addInsurance(insuranceName, userId);

        // Assert
        assertThat(result.getInsurances()).isNotNull();
        assertThat(result.getInsurances()).containsExactly(insurance);

        verify(patientRepository).getUserByIdOrElseThrow(userId);
        verify(insuranceRepository).getByIdOrElseThrow(insuranceName);
        verify(patientRepository).isPatientHasInsurance(userId, insuranceName);
        verify(patientRepository).saveUser(patient);
    }

    @Test
    void addInsurance_ShouldThrowException_WhenPatientAlreadyHasInsurance() {
        // Arrange
        Long userId = 1L;
        String insuranceName = "AXA";

        Patient patient = new Patient();
        patient.setUserId(userId);

        when(patientRepository.getUserByIdOrElseThrow(userId)).thenReturn(patient);
        when(patientRepository.isPatientHasInsurance(userId, insuranceName)).thenReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> patientAddInsurance.addInsurance(insuranceName, userId))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessageContaining("AXA");

        verify(patientRepository).getUserByIdOrElseThrow(userId);
        verify(patientRepository).isPatientHasInsurance(userId, insuranceName);
        verify(insuranceRepository).getByIdOrElseThrow(insuranceName);
        verify(patientRepository, never()).saveUser(any());
    }
}
