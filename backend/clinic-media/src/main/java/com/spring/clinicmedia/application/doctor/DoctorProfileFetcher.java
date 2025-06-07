package com.spring.clinicmedia.application.doctor;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.domain.port.repository.DoctorRepository;
import com.spring.clinicmedia.presentation.dto.profile.DoctorProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorProfileFetcher {

    private final DoctorRepository doctorUserRepository;

    public DoctorProfile execute(long userId) {


        Doctor doctor = doctorUserRepository.getUserByIdOrElseThrow(userId);

        return DoctorProfile.builder()
                .userName(doctor.getRegistration().getName())
                .email(doctor.getRegistration().getEmail())
                .build();
    }


}
