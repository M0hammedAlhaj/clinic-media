package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.enitity.Insurance;
import com.spring.clinicmedia.infrastructure.Jpa.InsuranceJpa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InsuranceRepositoryAdapterTest {

    @InjectMocks
    private InsuranceRepositoryAdapter underTest;

    @Mock
    private InsuranceJpa insuranceJpa;

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
    void save() {
        Insurance insurance = new Insurance();
        underTest.save(insurance);

        verify(insuranceJpa).save(insurance);
    }

    @Test
    void getByIdOrElseThrow_shouldReturnInsurance() {
        Insurance insurance = new Insurance();
        when(insuranceJpa.findById("bupa")).thenReturn(Optional.of(insurance));

        Insurance result = underTest.getByIdOrElseThrow("bupa");

        assertThat(result).isEqualTo(insurance);
        verify(insuranceJpa).findById("bupa");
    }

    @Test
    void getByIdOrElseThrow_shouldThrowException() {
        when(insuranceJpa.findById("bupa")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.getByIdOrElseThrow("bupa"))
                .isInstanceOf(ResourcesNotFoundException.class);

        verify(insuranceJpa).findById("bupa");
    }

    @Test
    void existsById() {
        when(insuranceJpa.existsById("bupa")).thenReturn(true);

        boolean exists = underTest.existsById("bupa");

        assertThat(exists).isTrue();
        verify(insuranceJpa).existsById("bupa");
    }

    @Test
    void findInsuranceByInsuranceNumber() {
        Insurance insurance = new Insurance();

        when(insuranceJpa.findById("bupa")).thenReturn(Optional.of(insurance));

        Optional<Insurance> result = underTest.findInsuranceByInsuranceNumber("bupa");

        assertThat(result).isEqualTo(Optional.of(insurance));
        verify(insuranceJpa).findById("bupa");
    }
}
