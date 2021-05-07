package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Slf4j
public class RedisMsgListenerService extends MessageListenerAdapter {
    public RedisMsgListenerService() {
        this.setDefaultListenerMethod("messageListener");
    }

    public void messageListener(String message) {
        log.info("receive from redis message: {}", message);
    }
}
