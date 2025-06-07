package com.spring.clinicmedia.application.request;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.domain.port.validator.UserActivationValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestFetcher {

    private final RequestRepository requestRepository;

    private final UserActivationValidator userActivationValidator;

    public List<ClinicDoctorRequest> execute(Long userId,
                                             UserType sender, int pageNumber) {

        userActivationValidator.validate(userId, sender);

        return requestRepository.findRequestsBySenderIdAndSenderType(userId,
                sender, PageRequest.of(pageNumber, 5));
    }

}
