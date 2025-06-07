package com.spring.clinicmedia.infrastructure.repository;

import com.spring.clinicmedia.domain.exception.ResourcesNotFoundException;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.ClinicDoctorRequest;
import com.spring.clinicmedia.domain.port.repository.RequestRepository;
import com.spring.clinicmedia.infrastructure.Jpa.RequestJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RequestRepositoryAdapter implements RequestRepository {

    private final RequestJpaRepository requestJpaRepository;

    @Override
    public ClinicDoctorRequest save(ClinicDoctorRequest clinicDoctorRequest) {

        return requestJpaRepository.save(clinicDoctorRequest);
    }

    @Override
    public ClinicDoctorRequest getByIdOrElseThrow(Long id) {
        return requestJpaRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Request not found"));
    }

    @Override
    public boolean existsById(Long id) {
        return requestJpaRepository.existsById(id);
    }

    @Override
    public Optional<ClinicDoctorRequest> findByClinicIdAndDoctorIdAndSender(long clinicId, long doctorId, UserType sender) {
        return requestJpaRepository.findByClinicUserIdAndDoctorUserIdAndSender(clinicId, doctorId, sender);
    }

    @Override
    public List<ClinicDoctorRequest> findRequestsBySenderIdAndSenderType(long senderId,
                                                                         UserType sender,
                                                                         Pageable pageable) {
        if (sender.equals(UserType.CLINIC))
            return requestJpaRepository.findRequestsByClinicUserIdAndSender(senderId, sender, pageable).getContent();

        return requestJpaRepository.findRequestsByDoctorUserIdAndSender(senderId, sender, pageable).getContent();
    }

    @Override
    public Optional<ClinicDoctorRequest> findByClinicIdAndDoctorID(long clinicId, long doctorId) {
        return requestJpaRepository.findByClinicUserIdAndDoctorUserId(clinicId, doctorId);
    }


}
