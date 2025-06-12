package com.spring.clinicmedia.application.request;

import com.spring.clinicmedia.domain.exception.ResourceAlreadyExistsException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.RequestValidator;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorClinicRequestServiceTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private UserActivationValidator userActivationValidator;

    @InjectMocks
    private DoctorClinicRequestService underTest;

    private Doctor doctor;
    private Clinic clinic;
    private long doctorId = 1L;
    private long clinicId = 2L;

    @BeforeEach
    void setup() {
        doctor = new Doctor();
        clinic = new Clinic();
    }

    @Test
    void createRequest_ShouldSaveRequest_WhenValidInputs() {
        // Arrange
        when(doctorRepository.getUserByIdOrElseThrow(doctorId)).thenReturn(doctor);
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(clinic);
        when(doctorRepository.isDoctorAssociatedWithClinic(doctorId, clinicId)).thenReturn(false);

        // Act
        underTest.createRequest(doctorId, clinicId, UserType.DOCTOR);

        // Assert
        verify(userActivationValidator).validateUserIsActive(doctorId, UserType.DOCTOR);
        verify(userActivationValidator).validateUserIsActive(clinicId, UserType.CLINIC);
        verify(requestValidator).validateRequestDoesNotExist(clinicId, doctorId);
        verify(requestRepository).save(any(ClinicDoctorRequest.class));
    }

    @Test
    void createRequest_ShouldThrowException_WhenDoctorAlreadyAssociatedWithClinic() {
        // Arrange
        when(doctorRepository.getUserByIdOrElseThrow(doctorId)).thenReturn(doctor);
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(clinic);
        when(doctorRepository.isDoctorAssociatedWithClinic(doctorId, clinicId)).thenReturn(true);

        // Act + Assert
        assertThatThrownBy(() -> underTest.createRequest(doctorId, clinicId, UserType.DOCTOR))
                .isInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessageContaining("Doctor is already associated with the clinic");

        verify(requestRepository, never()).save(any());
    }

    @Test
    void createRequest_ShouldCallUserValidators() {

        // Arrange
        when(doctorRepository.getUserByIdOrElseThrow(doctorId)).thenReturn(doctor);
        when(clinicRepository.getUserByIdOrElseThrow(clinicId)).thenReturn(clinic);
        when(doctorRepository.isDoctorAssociatedWithClinic(doctorId, clinicId)).thenReturn(false);

        // Act
        underTest.createRequest(doctorId, clinicId, UserType.DOCTOR);

        // Assert
        verify(userActivationValidator).validateUserIsActive(doctorId, UserType.DOCTOR);
        verify(userActivationValidator).validateUserIsActive(clinicId, UserType.CLINIC);
    }
}
