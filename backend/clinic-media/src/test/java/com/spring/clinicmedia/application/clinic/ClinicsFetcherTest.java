package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import com.spring.clinicmedia.presentation.dto.FilterSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicFetcherTest {

    @Mock
    private ClinicRepository clinicRepository;

    @InjectMocks
    private ClinicsFetcher underTest;


    @Test
    void getClinics_shouldReturnListFromRepository() {
        int pageNumber = 0;
        int pageSize = 10;
        FilterSpecification filterSpecification = mock(FilterSpecification.class);

        List<Clinic> expectedClinics = List.of(new Clinic(), new Clinic());

        when(clinicRepository.getClinicsByFilter(PageRequest.of(pageNumber, pageSize), filterSpecification))
                .thenReturn(expectedClinics);

        List<Clinic> actualClinics = underTest.getClinics(pageNumber, pageSize, filterSpecification);

        assertThat(actualClinics).isEqualTo(expectedClinics);

        verify(clinicRepository).getClinicsByFilter(PageRequest.of(pageNumber, pageSize), filterSpecification);
    }

    @Test
    void getClinics_shouldThrowException_whenPageNumberNegative() {
        int invalidPageNumber = -1;
        int pageSize = 10;
        FilterSpecification filterSpecification = mock(FilterSpecification.class);

        assertThatThrownBy(() -> underTest.getClinics(invalidPageNumber, pageSize, filterSpecification))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Page number must be >= 0");
    }

    @Test
    void getClinics_shouldThrowException_whenPageSizeNotPositive() {
        int pageNumber = 0;
        int invalidPageSize = 0;
        FilterSpecification filterSpecification = mock(FilterSpecification.class);

        assertThatThrownBy(() -> underTest.getClinics(pageNumber, invalidPageSize, filterSpecification))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("page size must be > 0");
    }
}
