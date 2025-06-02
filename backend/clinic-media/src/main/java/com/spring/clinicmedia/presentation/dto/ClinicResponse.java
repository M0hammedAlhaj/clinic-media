package com.spring.clinicmedia.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClinicResponse {

    private String clinicName;

    private List<String> speciality;

    private List<String> city;

}
