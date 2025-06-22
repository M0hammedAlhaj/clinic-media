package com.spring.clinicmedia.infrastructure.factory.notification;

import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.factory.NotificationFactory;
import com.spring.clinicmedia.domain.factory.SpecificNotificationCreator;
import com.spring.clinicmedia.domain.model.UserType;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationFactoryAdapter implements NotificationFactory {
    private final Map<UserType, SpecificNotificationCreator<? extends User>> specificCreators;

    public NotificationFactoryAdapter(List<SpecificNotificationCreator<? extends User>> creators) {
        this.specificCreators = creators.stream()
                .collect(Collectors.toMap(SpecificNotificationCreator::getUserType, c -> c));
    }

    @Override
    public Notification create(NotificationCreationCommand<? extends User> command) {
        SpecificNotificationCreator<? extends User> creator =
                specificCreators.get(command.getUserType());

        if (creator == null) {
            throw new IllegalArgumentException("Unsupported user type: " + command.getUserType());
        }


        @SuppressWarnings("unchecked")
        SpecificNotificationCreator<User> safeCreator = (SpecificNotificationCreator<User>) creator;

        @SuppressWarnings("unchecked")
        NotificationCreationCommand<User> safeCommand = (NotificationCreationCommand<User>) command;

        return safeCreator.create(safeCommand);
    }


}

