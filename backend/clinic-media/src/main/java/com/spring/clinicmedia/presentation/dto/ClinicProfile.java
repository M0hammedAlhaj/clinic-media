package com.spring.clinicmedia.presentation.dto;

import com.spring.clinicmedia.domain.model.enitity.user.Clinic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicProfile {


    private String name;

    private String userEmail;

    private List<String> locations;


}
