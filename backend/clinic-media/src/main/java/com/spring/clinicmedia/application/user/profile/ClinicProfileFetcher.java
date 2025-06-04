package com.spring.clinicmedia.application.user.profile;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClinicProfileFetcher {


    private final ClinicRepository clinicUserRepository;

    public Clinic execute(long userId) {

        return clinicUserRepository.getUserByIdOrElseThrow(userId);
    }


}
