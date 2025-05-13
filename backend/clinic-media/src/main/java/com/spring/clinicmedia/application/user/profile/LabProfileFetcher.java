package com.spring.clinicmedia.application.user.profile;


import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import com.spring.clinicmedia.domain.port.repository.LabRepository;
import com.spring.clinicmedia.presentation.dto.profile.LabProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabProfileFetcher {

    private final LabRepository labRepository;

    public LabProfile execute(long labId) {
        Lab lab = labRepository.getUserById(labId);

        return LabProfile.builder()
                .email(lab.getRegistration().getEmail())
                .userName(lab.getRegistration().getName())
                .build();
    }

}
