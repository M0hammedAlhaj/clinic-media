package com.spring.clinicmedia.infrastructure.validation;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpecialityExistValidatorTest {

    private SpecialityExistValidator underTest;

    @Mock
    private ClinicRepository clinicRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SpecialityExistValidator(clinicRepository);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void validateSpecialityAssignment_throwsException_whenSpecialityAlreadyExistsForClinic() {

        Clinic clinic = new Clinic();

        when(clinicRepository.findClinicByIdAndSpecialityName(1L, "speciality1"))
                .thenReturn(Optional.of(clinic));

        assertThatThrownBy(() -> underTest.validateSpecialityAssignment(1L, "speciality1"))
                .isInstanceOf(ResourceAlreadyExistsException.class);

        verify(clinicRepository).findClinicByIdAndSpecialityName(1L, "speciality1");

    }

    @Test
    void validateSpecialityAssignment_doesNotThrowException_whenSpecialityDoesNotExist() {

        when(clinicRepository.findClinicByIdAndSpecialityName(1L, "speciality1"))
                .thenReturn(Optional.empty());

        assertThatCode(() -> underTest.validateSpecialityAssignment(1L, "speciality1"))
                .doesNotThrowAnyException();

        verify(clinicRepository).findClinicByIdAndSpecialityName(1L, "speciality1");
    }

}