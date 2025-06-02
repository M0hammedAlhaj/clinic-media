package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.presentation.dto.FilterSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClinicsFetcher {

    private final ClinicRepository clinicRepository;


    public List<Clinic> getClinics(int pageNumber,
                                   int pageSize
            , FilterSpecification filterSpecification) {


        return clinicRepository.getClinicsByFilter(PageRequest.of(pageNumber, pageSize),
                filterSpecification);
    }

}
