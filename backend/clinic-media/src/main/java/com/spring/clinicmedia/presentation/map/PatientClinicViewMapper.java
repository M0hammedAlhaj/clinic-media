package com.spring.clinicmedia.presentation.map;


import com.spring.clinicmedia.domain.model.enitity.BookingDate;
import com.spring.clinicmedia.domain.model.enitity.user.Doctor;
import com.spring.clinicmedia.presentation.dto.BasicDoctorInformation;
import com.spring.clinicmedia.presentation.dto.PatientClinicView;
import com.spring.clinicmedia.presentation.dto.request.DoctorBookingDate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientClinicViewMapper {

    public static PatientClinicView createFrom(Map<Doctor,
            List<BookingDate>> bookingDatesDoctor) {

        return PatientClinicView.builder()
                .bookingDatesDoctor(create(bookingDatesDoctor))
                .build();
    }

    private static Map<BasicDoctorInformation, List<DoctorBookingDate>> create(
            Map<Doctor, List<BookingDate>> doctorBookingDates) {

        return doctorBookingDates.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> BasicDoctorInformation.builder()
                                .doctorId(entry.getKey().getUserId().toString())
                                .doctorName(entry.getKey().getRegistration().getName())
                                .build(),

                        entry -> entry.getValue().stream()
                                .map(bookingDate -> DoctorBookingDate.builder()
                                        .bookingDateResponse(BookingDateResponseMapper.createFrom(bookingDate))
                                        .location(bookingDate.getLocation().getCountryName())
                                        .build())
                                .collect(Collectors.toList())
                ));
    }
}


