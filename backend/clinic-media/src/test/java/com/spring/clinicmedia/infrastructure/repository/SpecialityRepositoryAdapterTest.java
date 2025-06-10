package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.infrastructure.Jpa.SpecialityJpa;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpecialityRepositoryAdapterTest {

    @InjectMocks
    private SpecialityRepositoryAdapter underTest;

    @Mock
    private SpecialityJpa specialityJpa;

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
    void save_shouldCallJpaAndReturnEntity() {
        Speciality speciality = new Speciality();
        when(specialityJpa.save(speciality)).thenReturn(speciality);

        Speciality result = underTest.save(speciality);

        assertThat(result).isEqualTo(speciality);
        verify(specialityJpa).save(speciality);
    }

    @Test
    void getByIdOrElseThrow_shouldReturnEntityWhenFound() {
        Speciality speciality = new Speciality();
        when(specialityJpa.findById("cardiology")).thenReturn(Optional.of(speciality));

        Speciality result = underTest.getByIdOrElseThrow("cardiology");

        assertThat(result).isEqualTo(speciality);
        verify(specialityJpa).findById("cardiology");
    }

    @Test
    void getByIdOrElseThrow_shouldThrowExceptionWhenNotFound() {
        when(specialityJpa.findById("not-exist")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getByIdOrElseThrow("not-exist"))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessageContaining("not-exist");

        verify(specialityJpa).findById("not-exist");
    }

    @Test
    void existsById_shouldReturnTrueWhenExists() {
        when(specialityJpa.existsById("dermatology")).thenReturn(true);

        boolean exists = underTest.existsById("dermatology");

        assertThat(exists).isTrue();
        verify(specialityJpa).existsById("dermatology");
    }

    @Test
    void existsById_shouldReturnFalseWhenNotExists() {
        when(specialityJpa.existsById("unknown")).thenReturn(false);

        boolean exists = underTest.existsById("unknown");

        assertThat(exists).isFalse();
        verify(specialityJpa).existsById("unknown");
    }
}
