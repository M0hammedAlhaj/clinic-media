package com.spring.clinicmedia.domain.command;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationCreationCommand<T extends User> {

    private UserType receiverType;

    private String message;

    private T user;


}
