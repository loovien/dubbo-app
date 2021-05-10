package com.example.websocket.service.impl;

import com.example.common.dto.message.MsgDTO;
import com.example.websocket.config.Initializr;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * created 5/10/2021 10:42 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@Service
public class MsgListenerServiceImpl implements MessageListener {

    private final Initializr initializr;

    private final ObjectMapper objectMapper;

    public MsgListenerServiceImpl(Initializr initializr, ObjectMapper objectMapper) {
        this.initializr = initializr;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {

        log.info("receive redis channel message: {}", message);
        try {
            MsgDTO msgDTO = objectMapper.readValue(message.getBody(), MsgDTO.class);
            String channelId = String.valueOf(msgDTO.getReceiver());
            WebSocketSession webSocketSession = initializr.getSockets().get(channelId);
            if (webSocketSession == null) {
                log.error("socket group not found channel id: {}", msgDTO.getReceiver());
                return;
            }
            TextMessage textMessage = new TextMessage(message.getBody());
            webSocketSession.sendMessage(textMessage);
        } catch (IOException e) {
            log.error("subscribe from redis channel message invalid: {}", message);
        }

    }
}
