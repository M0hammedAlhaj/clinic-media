package com.spring.clinicmedia.presentation.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveAccountResponse {

    private Long userId;

    private String userEmail;

}
