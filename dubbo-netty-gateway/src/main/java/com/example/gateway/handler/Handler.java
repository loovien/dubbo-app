package com.example.gateway.handler;

import com.example.common.dto.gateway.BizDTO;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

/**
 * created 5/8/2021 3:21 PM
 *
 * @author luowen <loovien@163.com>
 */
public interface Handler {
    void handler(ChannelHandlerContext context, BizDTO bizDTO) throws IOException;
}
