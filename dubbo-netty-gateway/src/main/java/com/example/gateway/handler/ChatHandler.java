package com.example.gateway.handler;

import com.example.common.dto.gateway.BizDTO;
import com.example.common.dto.message.MsgDTO;
import com.example.gateway.config.Initializr;
import com.example.gateway.tool.ConversionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created 5/8/2021 3:10 PM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@Service
public class ChatHandler implements Handler {

    private final Initializr initializr;

    private final ObjectMapper objectMapper;

    public ChatHandler(Initializr initializr, ObjectMapper objectMapper) {
        this.initializr = initializr;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handler(ChannelHandlerContext context, BizDTO bizDTO) throws IOException {
        MsgDTO msgDTO = objectMapper.readValue(bizDTO.getBody(), MsgDTO.class);
        // String channelId = ConversionUtil.getChannelId(msgDTO.getSender(), msgDTO.getReceiver());
        String channelId = String.valueOf(msgDTO.getReceiver());
        Channel channel = initializr.getSockets().get(channelId);
        if (channel == null) {
            log.error("conversion id: {} not online, skip it.", channelId);
            return;
        }
        channel.writeAndFlush(bizDTO);
    }
}
