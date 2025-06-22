package com.spring.clinicmedia.infrastructure;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
@Component
public class SessionManager {

    private final Set<String> sessions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public void connectionToWebSocket(String userName) {
        sessions.add(userName);
    }

    public Set<String> getSessions() {
        return Collections.unmodifiableSet(sessions);
    }

    public void disconnect(String userName) {
        sessions.remove(userName);
    }

}
