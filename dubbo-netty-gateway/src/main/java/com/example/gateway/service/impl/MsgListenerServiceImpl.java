package com.example.gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MsgListenerServiceImpl extends MessageListenerAdapter {

    public MsgListenerServiceImpl() {
        this.setDefaultListenerMethod("msgHandler");
    }

    public void msgHandler(String message) {
        log.info("receive redis channel message: {}", message);
    }
}
