package com.spring.clinicmedia.domain.command;

import com.spring.clinicmedia.domain.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationCommand {

    private String name;

    private UserType userType;

    private String email;

    private String password;

}
