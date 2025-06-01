package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.infrastructure.Jpa.RequestJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RequestRepositoryAdapter implements RequestRepository {

    private final RequestJpaRepository requestJpaRepository;

    @Override
    public Request save(Request request) {

        return requestJpaRepository.save(request);
    }

    @Override
    public Request getById(Long id) {
        return requestJpaRepository.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("Request not found"));
    }

    @Override
    public Optional<Request> findByClinicIdAndDoctorIdAndSender(long clinicId, long doctorId, UserType sender) {
        return requestJpaRepository.findByClinicUserIdAndDoctorUserIdAndSender(clinicId, doctorId, sender);
    }

    @Override
    public Page<Request> findRequestsBySenderIdAndSenderType(long senderId,
                                                             UserType sender,
                                                             Pageable pageable) {
        if (sender.equals(UserType.CLINIC))
            return requestJpaRepository.findRequestsByClinicUserIdAndSender(senderId, sender, pageable);

        return requestJpaRepository.findRequestsByDoctorUserIdAndSender(senderId, sender, pageable);
    }

    @Override
    public Optional<Request> findByClinicIdAndDoctorID(long clinicId, long doctorId) {
        return requestJpaRepository.findByClinicUserIdAndDoctorUserId(clinicId, doctorId);
    }


}
