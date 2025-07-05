package com.spring.clinicmedia.presentation.coinfigration.websockets;

import com.spring.clinicmedia.infrastructure.SessionManager;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Deprecated
@Component
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private final SessionManager sessionManager;

    public WebSocketHandshakeInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        String query = request.getURI()
                .getQuery(); // e.g. userName=mohammed

        if (query != null && query.contains("userName=")) {
            String userName = query.split("userName=")[1]
                    .split("&")[0];
            sessionManager.connectionToWebSocket(userName);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {

    }
}
