package com.spring.clinicmedia.application.insurance.users;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ClinicAddInsuranceTest {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private ClinicAddInsurance clinicAddInsurance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addInsurance_ShouldAddInsurance_WhenClinicDoesNotHaveInsurance() {
        // Arrange
        Long userId = 1L;
        String insuranceName = "AXA";

        Insurance insurance = Insurance.builder()
                .insuranceName(insuranceName)
                .build();

        Clinic clinic = new Clinic();
        clinic.setUserId(userId);
        clinic.setInsurances(null);  // initially null

        when(insuranceRepository.getByIdOrElseThrow(insuranceName)).thenReturn(insurance);
        when(clinicRepository.getUserByIdOrElseThrow(userId)).thenReturn(clinic);
        when(clinicRepository.isClinicHasInsurance(userId, insuranceName)).thenReturn(false);
        when(clinicRepository.saveUser(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = clinicAddInsurance.addInsurance(insuranceName, userId);

        // Assert
        assertThat(result).isInstanceOf(Clinic.class);
        Clinic savedClinic = (Clinic) result;
        assertThat(savedClinic.getInsurances()).contains(insurance);

        verify(insuranceRepository).getByIdOrElseThrow(insuranceName);
        verify(clinicRepository).getUserByIdOrElseThrow(userId);
        verify(clinicRepository).isClinicHasInsurance(userId, insuranceName);
        verify(clinicRepository).saveUser(any());
    }

    @Test
    void addInsurance_ShouldThrowException_WhenClinicAlreadyHasInsurance() {
        // Arrange
        Long userId = 1L;
        String insuranceName = "AXA";

        Insurance insurance = Insurance.builder()
                .insuranceName(insuranceName)
                .build();

        Clinic clinic = new Clinic();
        clinic.setUserId(userId);

        when(insuranceRepository.getByIdOrElseThrow(insuranceName)).thenReturn(insurance);
        when(clinicRepository.getUserByIdOrElseThrow(userId)).thenReturn(clinic);
        when(clinicRepository.isClinicHasInsurance(userId, insuranceName)).thenReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> clinicAddInsurance.addInsurance(insuranceName, userId))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessageContaining(insuranceName);

        verify(insuranceRepository).getByIdOrElseThrow(insuranceName);
        verify(clinicRepository).getUserByIdOrElseThrow(userId);
        verify(clinicRepository).isClinicHasInsurance(userId, insuranceName);
        verifyNoMoreInteractions(clinicRepository);
    }
}
