package com.spring.clinicmedia.domain.result;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginResult {

    private String userEmail;

    private String token;

}
