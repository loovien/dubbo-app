package com.example.websocket.config;

import com.example.websocket.controller.DubboWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * created 5/10/2021 10:00 AM
 *
 * @author luowen <loovien@163.com>
 */
@EnableWebSocket
@Configuration(proxyBeanMethods = false)
public class WebSocketConfigure implements WebSocketConfigurer {

    private final DubboWebSocketHandler webSocketHandler;

    public WebSocketConfigure(DubboWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
