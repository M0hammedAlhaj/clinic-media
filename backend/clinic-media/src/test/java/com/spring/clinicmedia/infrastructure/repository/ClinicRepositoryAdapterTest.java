package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.infrastructure.Jpa.ClinicJpa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ClinicRepositoryAdapterTest {

    @InjectMocks
    private ClinicRepositoryAdapter underTest;

    @Mock
    private ClinicJpa clinicJpa;

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
    void findUserByEmail() {
        String email = "test@clinic.com";
        underTest.findUserByEmail(email);
        verify(clinicJpa).findClinicByRegistrationEmail(email);
    }

    @Test
    void saveUser() {
        Clinic user = new Clinic();
        underTest.saveUser(user);
        verify(clinicJpa).save(user);
    }

    @Test
    void getUserByActive() {
        underTest.getUserByActive(false, Pageable.ofSize(5));
        verify(clinicJpa).findByIsActive(false, Pageable.ofSize(5));

        underTest.getUserByActive(true, Pageable.ofSize(5));
        verify(clinicJpa).findByIsActive(true, Pageable.ofSize(5));
    }

    @Test
    void getUserByIdOrElseThrow_shouldReturnClinic() {
        Clinic clinic = new Clinic();
        when(clinicJpa.findById(1L)).thenReturn(Optional.of(clinic));

        Clinic result = underTest.getUserByIdOrElseThrow(1L);
        assertThat(result).isEqualTo(clinic);
        verify(clinicJpa).findById(1L);
    }

    @Test
    void getUserByIdOrElseThrow_shouldThrowException() {
        when(clinicJpa.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByIdOrElseThrow(1L))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(clinicJpa).findById(1L);
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldReturnClinic() {
        Clinic clinic = new Clinic();
        when(clinicJpa.findClinicByRegistrationEmail("email")).thenReturn(Optional.of(clinic));

        Clinic result = underTest.getUserByUserEmailOrElseThrow("email");
        assertThat(result).isEqualTo(clinic);

        verify(clinicJpa).findClinicByRegistrationEmail("email");
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldThrowException() {
        when(clinicJpa.findClinicByRegistrationEmail("email")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByUserEmailOrElseThrow("email"))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(clinicJpa).findClinicByRegistrationEmail("email");
    }

    @Test
    void existsByUserId_shouldCallJpa() {
        underTest.existsByUserId(1L);

        verify(clinicJpa).existsById(1L);
    }

    @Test
    void getClinicsByFilter_shouldReturnClinics() {
        Pageable pageable = Pageable.ofSize(5);
        Specification<Clinic> specification = mock(Specification.class);

        Clinic clinic = new Clinic();
        List<Clinic> clinics = List.of(clinic);
        Page<Clinic> clinicPage = new PageImpl<>(clinics);

        when(clinicJpa.findAll(specification, pageable))
                .thenReturn(clinicPage);

        List<Clinic> result = underTest.getClinicsByFilter(pageable, specification);

        assertThat(result).isEqualTo(clinics);
        verify(clinicJpa).findAll(specification, pageable);
    }


    @Test
    void findClinicByIdAndSpecialityName_shouldCallJpa() {
        underTest.findClinicByIdAndSpecialityName(1L, "Cardiology");
        verify(clinicJpa).searchBySpecialitiesSpecialityNameAndUserId("Cardiology", 1L);
    }
}
