package com.spring.clinicmedia.domain.factory;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.user.User;

public interface SpecificNotificationCreator<T extends User> {

    UserType getUserType();

    Notification create(NotificationCreationCommand<T> notification);
}
