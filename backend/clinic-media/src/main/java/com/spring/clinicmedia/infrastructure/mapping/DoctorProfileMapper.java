package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.presentation.dto.profile.DoctorProfile;

public class DoctorProfileMapper {


    public static DoctorProfile createFrom(Doctor doctor) {

        return DoctorProfile.builder()
                .email(doctor.getRegistration().getEmail())
                .userName(doctor.getRegistration().getName())
                .build();
    }
}
