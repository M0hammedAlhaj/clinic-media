package com.spring.clinicmedia.infrastructure;

import com.spring.clinicmedia.application.PresenceService;
import com.spring.clinicmedia.domain.port.Notification;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationBrowser implements Notification {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final PresenceService presenceService;

    //user/{username}/queue/notifications
    @Override
    public void notify(String message, String userName) {
        if (presenceService.isUserOnline(userName)) {
            simpMessagingTemplate.convertAndSendToUser(userName, "/queue/notifications", message);
        } else {
        //
        }
    }
}
