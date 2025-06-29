package com.spring.clinicmedia.application.lab;


import com.spring.clinicmedia.domain.model.enitity.user.Lab;
import com.spring.clinicmedia.domain.repository.user.LabRepository;
import com.spring.clinicmedia.presentation.dto.profile.LabProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabProfileFetcher {

    private final LabRepository labRepository;

    public LabProfile execute(long labId) {
        Lab lab = labRepository.getUserByIdOrElseThrow(labId);

        return LabProfile.builder()
                .email(lab.getRegistration().getEmail())
                .userName(lab.getRegistration().getName())
                .build();
    }

}
