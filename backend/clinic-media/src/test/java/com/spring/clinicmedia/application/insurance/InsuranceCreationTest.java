package com.spring.clinicmedia.application.insurance;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.domain.port.repository.InsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class InsuranceCreationTest {

    @Mock
    InsuranceRepository insuranceRepository;

    @InjectMocks
    InsuranceCreation underTest;


    @Test
    void execute_shouldThrowException_whenInsuranceExists() {

        String insuranceName = "Insurance Name";

        when(insuranceRepository.findInsuranceByInsuranceNumber(insuranceName))
                .thenReturn(Optional.of(new Insurance()));

        assertThatThrownBy(() -> underTest.execute(insuranceName))
                .isInstanceOf(ResourceAlreadyExistsException.class);

        verify(insuranceRepository).findInsuranceByInsuranceNumber(insuranceName);

        verify(insuranceRepository, never()).save(any());

    }


    @Test
    void execute_shouldSaveAndReturnName_whenInsuranceDoesNotExist() {
        String insuranceName = "Insurance Name";

        Insurance insurance = Insurance.builder()
                .insuranceName(insuranceName).build();

        when(insuranceRepository.findInsuranceByInsuranceNumber(insuranceName))
                .thenReturn(Optional.empty());

        when(insuranceRepository.save(insurance)).thenReturn(insurance);

        assertThat(underTest.execute(insuranceName))
                .isNotNull();

        verify(insuranceRepository).findInsuranceByInsuranceNumber(insuranceName);
        verify(insuranceRepository).save(insurance);
    }
}