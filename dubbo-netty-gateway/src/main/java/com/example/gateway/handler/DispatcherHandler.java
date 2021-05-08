package com.example.gateway.handler;

import com.example.common.dto.gateway.BizDTO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * created 5/8/2021 10:42 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@ChannelHandler.Sharable
public class DispatcherHandler extends SimpleChannelInboundHandler<BizDTO> {
    private final ApplicationContext applicationContext;

    public DispatcherHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BizDTO bizDTO) throws Exception {
        log.info("dispatch handler: {}", new String(bizDTO.getBody()));
        for (Handlers handler : Handlers.values()) {
            if (handler.getCommand() == bizDTO.getCommand()) {
                log.info("find handler: {}, cmd: {}, body: {}", handler.getClass().getName(), bizDTO.getCommand(),
                        new String(bizDTO.getBody()));
                Handler handler1 = applicationContext.getBean(handler.getHandler());
                handler1.handler(channelHandlerContext, bizDTO);
                return;
            }
        }
        log.error("not handler process command: {} data: {}", bizDTO.getCommand(), new String(bizDTO.getBody()));
    }
}
