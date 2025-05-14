package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
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

    public List<Request> execute(Long userId,
                                 UserType sender, int pageNumber) {

        userActivationValidator.validate(userId, sender);

        return requestRepository.findRequestsBySenderIdAndSenderType(userId,
                        sender, PageRequest.of(pageNumber, 5))
                .getContent();

    }
}
