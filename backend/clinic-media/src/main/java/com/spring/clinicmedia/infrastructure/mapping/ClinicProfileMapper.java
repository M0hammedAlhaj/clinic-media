package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.presentation.dto.profile.ClinicProfile;

public class ClinicProfileMapper {

    public static ClinicProfile toClinicProfile(Clinic clinic) {
        return ClinicProfile.builder()
                .locations(LocationMapper.toListStringLocations(clinic.getClincLocations()))
                .userEmail(clinic.getRegistration().getEmail())
                .name(clinic.getRegistration().getName())
                .build();
    }

}
