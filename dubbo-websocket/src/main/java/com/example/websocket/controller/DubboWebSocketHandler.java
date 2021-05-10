package com.example.websocket.controller;

import com.example.common.dto.gateway.ConnDTO;
import com.example.websocket.config.Initializr;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * created 5/10/2021 10:14 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@Controller
public class DubboWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final Initializr initializr;

    public DubboWebSocketHandler(ObjectMapper objectMapper, Initializr initializr) {
        this.objectMapper = objectMapper;
        this.initializr = initializr;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connection: {} established", session.getRemoteAddress());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("remote websocket channel: {}", session.getRemoteAddress());
        initializr.getSockets().remove("1");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("receive message from web client: {}, message: {}", session.getRemoteAddress(), message.toString());
        ConnDTO connDTO = objectMapper.readValue(message.asBytes(), ConnDTO.class);
        if (!connDTO.getToken().equals("abc123")) {
            session.close();
            return;
        }
        log.info("add websocket session to map: {}", session.getRemoteAddress());
        initializr.getSockets().put("1", session);
    }
}
