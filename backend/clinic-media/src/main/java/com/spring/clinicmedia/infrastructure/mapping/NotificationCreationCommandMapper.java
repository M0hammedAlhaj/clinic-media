package com.spring.clinicmedia.infrastructure.mapping;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;

public class NotificationCreationCommandMapper {

    public static Notification assignData(NotificationCreationCommand<?> command,
                                          Notification notification) {
        notification.setMessage(command.getMessage());
        return notification;
    }
}
