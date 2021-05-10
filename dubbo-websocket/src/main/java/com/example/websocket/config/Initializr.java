package com.example.websocket.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created 5/10/2021 10:39 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Component
public class Initializr {
    private final Map<String, WebSocketSession> sockets = new ConcurrentHashMap<>();
}
