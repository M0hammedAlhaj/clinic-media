package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Request;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.infrastructure.repositoryJpa.RequestJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RequestRepositoryAdapter implements RequestRepository {

    private final RequestJpaRepository requestJpaRepository;

    @Override
    public void save(Request request) {
        requestJpaRepository.save(request);
    }

    @Override
    public Optional<Request> findByClinicIdAndDoctorIdAndSender(long clinicId, long doctorId, UserType sender) {
        return requestJpaRepository.findByClinicUserIdAndDoctorUserIdAndSender(clinicId, doctorId, sender);
    }


}
