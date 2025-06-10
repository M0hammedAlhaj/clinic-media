package com.spring.clinicmedia.application.doctor;

import com.spring.clinicmedia.application.doctor.DoctorProfileFetcher;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DoctorProfileFetcherTest {

    @Mock
    private DoctorRepository doctorRepository;

    private DoctorProfileFetcher underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DoctorProfileFetcher(doctorRepository);
    }

    @Test
    void execute_shouldReturnDoctor_whenDoctorExists() {

        underTest.execute(1L);

        verify(doctorRepository).getUserByIdOrElseThrow(1L);
    }
}
