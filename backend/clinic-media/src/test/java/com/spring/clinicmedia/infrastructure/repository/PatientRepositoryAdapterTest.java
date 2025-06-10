package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Patient;
import com.spring.clinicmedia.infrastructure.Jpa.PatientJpa;
import org.junit.jupiter.api.*;
import org.mockito.*;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientRepositoryAdapterTest {

    @InjectMocks
    private PatientRepositoryAdapter underTest;

    @Mock
    private PatientJpa patientJpa;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void findUserByEmail_shouldCallJpa() {
        underTest.findUserByEmail("email@example.com");

        verify(patientJpa).findByRegistrationEmail("email@example.com");
    }

    @Test
    void saveUser_shouldCallJpaSave() {
        Patient patient = new Patient();
        underTest.saveUser(patient);

        verify(patientJpa).save(patient);
    }

    @Test
    void getUserByActive_shouldCallJpa() {
        underTest.getUserByActive(true, Pageable.ofSize(10));
        verify(patientJpa).findByIsActive(true, Pageable.ofSize(10));

        underTest.getUserByActive(false, Pageable.ofSize(5));
        verify(patientJpa).findByIsActive(false, Pageable.ofSize(5));
    }

    @Test
    void getUserByIdOrElseThrow_shouldReturnPatient() {
        Patient patient = new Patient();
        when(patientJpa.findById(1L)).thenReturn(Optional.of(patient));

        Patient result = underTest.getUserByIdOrElseThrow(1L);

        assertThat(result).isEqualTo(patient);
        verify(patientJpa).findById(1L);
    }

    @Test
    void getUserByIdOrElseThrow_shouldThrowException() {
        when(patientJpa.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByIdOrElseThrow(1L))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(patientJpa).findById(1L);
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldReturnPatient() {
        Patient patient = new Patient();
        when(patientJpa.findByRegistrationEmail("email@example.com")).thenReturn(Optional.of(patient));

        Patient result = underTest.getUserByUserEmailOrElseThrow("email@example.com");

        assertThat(result).isEqualTo(patient);
        verify(patientJpa).findByRegistrationEmail("email@example.com");
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldThrowException() {
        when(patientJpa.findByRegistrationEmail("email@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByUserEmailOrElseThrow("email@example.com"))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(patientJpa).findByRegistrationEmail("email@example.com");
    }

    @Test
    void existsByUserId_shouldReturnTrue() {
        when(patientJpa.existsById(1L)).thenReturn(true);

        boolean exists = underTest.existsByUserId(1L);

        assertThat(exists).isTrue();
        verify(patientJpa).existsById(1L);
    }

    @Test
    void existsByUserId_shouldReturnFalse() {
        when(patientJpa.existsById(1L)).thenReturn(false);

        boolean exists = underTest.existsByUserId(1L);

        assertThat(exists).isFalse();
        verify(patientJpa).existsById(1L);
    }
}
