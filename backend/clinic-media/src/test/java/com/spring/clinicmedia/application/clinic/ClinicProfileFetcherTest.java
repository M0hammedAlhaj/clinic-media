package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.repository.user.ClinicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ClinicProfileFetcherTest {

    private ClinicProfileFetcher underTest;

    private ClinicRepository clinicRepositoryAdapter;


    @BeforeEach
    void setUp() {
        clinicRepositoryAdapter = mock(ClinicRepository.class);
        underTest = new ClinicProfileFetcher(clinicRepositoryAdapter);
    }

    @Test
    void execute_shouldCallRepositoryWithCorrectId() {
        underTest.execute(1L);
        verify(clinicRepositoryAdapter).getUserByIdOrElseThrow(1L);
    }

}