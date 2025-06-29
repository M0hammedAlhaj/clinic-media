package com.spring.clinicmedia.presentation.map;

import com.spring.clinicmedia.domain.model.enitity.Location;
import com.spring.clinicmedia.domain.model.enitity.Speciality;
import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import com.spring.clinicmedia.presentation.dto.ClinicResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ClinicResponseMapper {

    public static List<ClinicResponse> createFrom(List<Clinic> clinics) {
        return clinics.stream()
                .map(ClinicResponseMapper::createFrom)
                .collect(Collectors.toList());
    }

    public static ClinicResponse createFrom(Clinic clinic) {
        return ClinicResponse.builder()
                .clinicName(clinic.getRegistration().getName())
                .city(clinic.getClinicLocations()
                        .stream()
                        .map(Location::getCountryName)
                        .collect(Collectors.toList()))
                .speciality(clinic.getSpecialities()
                        .stream()
                        .map(Speciality::getSpecialityName)
                        .collect(Collectors.toList()))
                .build();
    }

}
