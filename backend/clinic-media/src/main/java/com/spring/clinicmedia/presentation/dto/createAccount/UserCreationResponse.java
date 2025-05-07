package com.spring.clinicmedia.presentation.dto.createAccount;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationResponse {

    private Long userId;

    private String email;

}
