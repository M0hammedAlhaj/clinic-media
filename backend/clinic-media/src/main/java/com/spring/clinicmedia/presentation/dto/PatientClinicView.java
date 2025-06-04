package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.presentation.dto.request.DoctorBookingDate;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class PatientClinicView {

    Map<BasicDoctorInformation, List<DoctorBookingDate>> bookingDatesDoctor;
}
