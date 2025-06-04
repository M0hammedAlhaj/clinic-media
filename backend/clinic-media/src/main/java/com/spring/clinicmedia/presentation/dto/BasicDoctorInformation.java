package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BasicDoctorInformation {

    private String doctorId;

    private String doctorName;


}
