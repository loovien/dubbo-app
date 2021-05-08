package com.example.gateway.handler;

import com.example.common.dto.gateway.BizDTO;
import com.example.common.dto.gateway.ConnDTO;
import com.example.common.util.JwtUtils;
import com.example.gateway.config.Initializr;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created 5/8/2021 4:04 PM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@Service
public class UserHandler implements Handler {

    private final ObjectMapper objectMapper;

    private final Initializr initializr;

    public UserHandler(ObjectMapper objectMapper, Initializr initializr) {
        this.objectMapper = objectMapper;
        this.initializr = initializr;
    }

    @Override
    public void handler(ChannelHandlerContext context, BizDTO bizDTO) throws IOException {
        ConnDTO connDTO = objectMapper.readValue(bizDTO.getBody(), ConnDTO.class);
        try {
            if (connDTO.getToken().equals("abc123")) {
                log.info("add socket to channel: {} with token: {}", context.channel().remoteAddress(), connDTO.getToken());
                initializr.getChannels().add(context.channel());
            }
            // Integer uid = JwtUtils.getId(connDTO.getToken());
            // initializr.getChannels().add(context.channel());
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error("UserHandler connection failure, data:{},  err: {}", new String(bizDTO.getBody()), exception.getMessage());
        }
    }
}
