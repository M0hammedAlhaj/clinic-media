package com.spring.clinicmedia.presentation.coinfigration.websockets;

import com.spring.clinicmedia.infrastructure.SessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.security.Principal;

@Deprecated
@Configuration
public class StompDisconnectInterceptor implements ChannelInterceptor {

    private final SessionManager sessionManager;

    public StompDisconnectInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public Message<?> preSend(Message<?> message,
                              MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            Principal user = accessor.getUser();
            if (user != null) {
                sessionManager.disconnect(user.getName());

            }
        }

        return message;
    }
}
