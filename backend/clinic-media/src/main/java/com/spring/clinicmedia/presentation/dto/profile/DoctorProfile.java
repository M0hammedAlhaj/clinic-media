package com.spring.clinicmedia.presentation.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorProfile {

    private String email;

    private String userName;


}
