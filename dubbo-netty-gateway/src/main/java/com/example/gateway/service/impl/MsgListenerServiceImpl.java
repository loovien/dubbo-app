package com.example.gateway.service.impl;

import com.example.common.dto.message.MsgDTO;
import com.example.gateway.config.Initializr;
import com.example.gateway.tool.BizDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
            Channel channel = initializr.getSockets().get(channelId);
            if (channel == null) {
                log.error("socket group not found channel id: {}", msgDTO.getReceiver());
                return;
            }
            channel.writeAndFlush(BizDataUtil.builder(message.getBody()));
        } catch (IOException e) {
            log.error("subscribe from redis channel message invalid: {}", message);
        }


    }
}
