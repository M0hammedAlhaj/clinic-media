package com.spring.clinicmedia.application;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.Location;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.domain.port.repository.UserRepository;
import com.spring.clinicmedia.infrastructure.UserRepositoryDispatcher;
import com.spring.clinicmedia.infrastructure.mapping.ClinicProfileMapper;
import com.spring.clinicmedia.presentation.dto.ClinicProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClinicProfileFetcher {


    private final UserRepositoryDispatcher userRepositoryDispatcher;

    public ClinicProfile execute(int userId) {

        UserRepository<Clinic> clinicUserRepository =
                userRepositoryDispatcher.getRepository(UserType.CLINIC);

        Clinic clinic = clinicUserRepository.getUserById(userId);

        return ClinicProfileMapper.toClinicProfile(clinic);
    }


}
