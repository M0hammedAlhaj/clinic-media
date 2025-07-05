package com.spring.clinicmedia.application;

import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PresenceService {

    private final SimpUserRegistry simpUserRegistry;

    public boolean isUserOnline(String userName) {
        return simpUserRegistry.getUser(userName) != null;
    }

}
