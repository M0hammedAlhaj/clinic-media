package com.spring.clinicmedia.infrastructure.notifications;

import com.spring.clinicmedia.domain.NotificationSender;
import com.spring.clinicmedia.domain.command.NotificationCreationCommand;
import com.spring.clinicmedia.domain.factory.NotificationFactory;
import com.spring.clinicmedia.domain.model.enitity.notifications.Notification;
import com.spring.clinicmedia.domain.model.enitity.user.User;
import com.spring.clinicmedia.domain.repository.notifications.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class NotificationProcessor {

    private final NotificationRepositoryDispatcher notificationRepositoryDispatcher;

    private final NotificationFactory factory;

    private final NotificationSender notificationSender;

    @Transactional
    public void createNotification(NotificationCreationCommand<User> command) {


        @SuppressWarnings("unchecked")
        NotificationRepository<Notification> notificationRepository =
                (NotificationRepository<Notification>) notificationRepositoryDispatcher.getRepository(command.getReceiverType());

        Notification notification = factory.create(command);
        notificationRepository.save(notification);

        boolean isNotify = notificationSender.isNotify(command.getMessage(),
                command.getUser().getRegistration().getEmail());

        if (!isNotify) {
            notification.setReceive(false);
            notificationRepository.save(notification);
        }
    }
}
