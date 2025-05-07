package com.spring.clinicmedia.presentation.dto.login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserLoginRequest {

    private String email;

    private String password;

}
