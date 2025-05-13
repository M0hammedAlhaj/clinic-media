package com.spring.clinicmedia.presentation.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LabProfile {

    private String email;

    private String userName;

}
