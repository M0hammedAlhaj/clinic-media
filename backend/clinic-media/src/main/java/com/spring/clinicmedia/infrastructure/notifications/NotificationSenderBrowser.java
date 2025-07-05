package com.spring.clinicmedia.infrastructure.notifications;

import com.spring.clinicmedia.application.PresenceService;
import com.spring.clinicmedia.domain.NotificationSender;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationSenderBrowser implements NotificationSender {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final PresenceService presenceService;


    //user/{username}/queue/notifications
    @Override
    public boolean isNotify(String message,
                          String receiverUserName) {
        if (presenceService.isUserOnline(receiverUserName)) {
            simpMessagingTemplate.convertAndSendToUser(receiverUserName,
                    "/queue/notifications",
                    message);
            return true;
        }
        return false;
    }

}
