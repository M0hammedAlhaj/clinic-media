package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.infrastructure.Jpa.users.DoctorJpa;
import com.spring.clinicmedia.infrastructure.repository.users.DoctorRepositoryAdapter;
import org.junit.jupiter.api.*;
import org.mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorRepositoryAdapterTest {

    @InjectMocks
    private DoctorRepositoryAdapter underTest;

    @Mock
    private DoctorJpa doctorJpa;

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
    void findUserByEmail_shouldReturnDoctor() {

        String email = "doc@example.com";
        Doctor doctor = new Doctor();

        when(doctorJpa.findByRegistrationEmail(email))
                .thenReturn(Optional.of(doctor));

        assertThat(underTest.findUserByEmail(email)).contains(doctor);
        verify(doctorJpa).findByRegistrationEmail(email);
    }

    @Test
    void saveUser_shouldSaveDoctor() {
        Doctor doctor = new Doctor();
        when(doctorJpa.save(doctor)).thenReturn(doctor);

        Doctor saved = underTest.saveUser(doctor);
        assertThat(saved).isEqualTo(doctor);
        verify(doctorJpa).save(doctor);

    }

    @Test
    void getUserByActive_shouldReturnDoctors() {
        Doctor doctor = new Doctor();
        Page<Doctor> doctorPage = new PageImpl<>(List.of(doctor));
        Pageable pageable = Pageable.ofSize(10);

        when(doctorJpa.findByIsActive(true, pageable)).thenReturn(doctorPage);

        Page<Doctor> result = underTest.getUserByActive(true, pageable);
        assertThat(result.getContent()).containsExactly(doctor);
        verify(doctorJpa).findByIsActive(true, pageable);
    }

    @Test
    void getUserByIdOrElseThrow_shouldReturnDoctor() {
        Doctor doctor = new Doctor();
        when(doctorJpa.findById(1L)).thenReturn(Optional.of(doctor));

        Doctor result = underTest.getUserByIdOrElseThrow(1L);
        assertThat(result).isEqualTo(doctor);
        verify(doctorJpa).findById(1L);
    }

    @Test
    void getUserByIdOrElseThrow_shouldThrowException() {
        when(doctorJpa.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByIdOrElseThrow(1L))
                .isInstanceOf(ResourcesNotFoundException.class);
        verify(doctorJpa).findById(1L);
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldReturnDoctor() {
        String email = "doc@example.com";
        Doctor doctor = new Doctor();
        when(doctorJpa.findByRegistrationEmail(email)).thenReturn(Optional.of(doctor));

        Doctor result = underTest.getUserByUserEmailOrElseThrow(email);
        assertThat(result).isEqualTo(doctor);
        verify(doctorJpa).findByRegistrationEmail(email);
    }

    @Test
    void getUserByUserEmailOrElseThrow_shouldThrowException() {
        when(doctorJpa.findByRegistrationEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getUserByUserEmailOrElseThrow("notfound@example.com"))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(doctorJpa).findByRegistrationEmail("notfound@example.com");
    }

    @Test
    void existsByUserId_shouldReturnTrue() {
        when(doctorJpa.existsById(1L)).thenReturn(true);

        assertThat(underTest.existsByUserId(1L)).isTrue();
        verify(doctorJpa).existsById(1L);
    }

    @Test
    void isDoctorAssociatedWithClinic_shouldReturnTrue() {
        when(doctorJpa.isDoctorAssociatedWithClinic(1L, 2L)).thenReturn(true);

        assertThat(underTest.isDoctorAssociatedWithClinic(1L, 2L)).isTrue();
        verify(doctorJpa).isDoctorAssociatedWithClinic(1L, 2L);
    }

}
