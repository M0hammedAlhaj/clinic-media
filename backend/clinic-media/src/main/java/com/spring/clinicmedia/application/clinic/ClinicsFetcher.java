package com.spring.clinicmedia.application.clinic;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.user.ClinicRepository;
import com.spring.clinicmedia.presentation.dto.FilterSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClinicsFetcher {

    private final ClinicRepository clinicRepository;

    /**
     * Retrieves a paginated list of clinics filtered by the given specification.
     *
     * @param pageNumber          the page number (zero-based)
     * @param pageSize            the number of clinics per page
     * @param filterSpecification the filtering criteria
     * @return a list of clinics matching the filter and pagination
     * @throws IllegalArgumentException if pageNumber or pageSize are negative
     */
    public List<Clinic> getClinics(int pageNumber,
                                   int pageSize,
                                   FilterSpecification filterSpecification) {

        if (pageNumber < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Page number must be >= 0 and page size must be > 0");
        }

        return clinicRepository.getClinicsByFilter(PageRequest.of(pageNumber, pageSize),
                filterSpecification);
    }


}
