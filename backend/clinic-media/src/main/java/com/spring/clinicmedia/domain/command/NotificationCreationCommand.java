package com.spring.clinicmedia.domain.command;

import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationCreationCommand<T extends User> {

    private UserType userType;

    private String message;

    private LocalDateTime localDateTime;

    private boolean isReceive;

    private T user;


}
