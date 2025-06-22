package com.spring.clinicmedia.domain.factory;


import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.user.User;

public interface NotificationFactory extends Creator<Notification, NotificationCreationCommand<? extends User>> {
    Notification create(NotificationCreationCommand<? extends User> command);
}

