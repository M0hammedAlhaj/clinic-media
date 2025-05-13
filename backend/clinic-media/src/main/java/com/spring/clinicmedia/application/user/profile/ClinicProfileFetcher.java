package com.spring.clinicmedia.application.user.profile;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import com.spring.clinicmedia.infrastructure.mapping.ClinicProfileMapper;
import com.spring.clinicmedia.presentation.dto.profile.ClinicProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClinicProfileFetcher {


    private final ClinicRepository clinicUserRepository;

    public ClinicProfile execute(long  userId) {

        Clinic clinic = clinicUserRepository.getUserById(userId);

        return ClinicProfileMapper.toClinicProfile(clinic);
    }


}
