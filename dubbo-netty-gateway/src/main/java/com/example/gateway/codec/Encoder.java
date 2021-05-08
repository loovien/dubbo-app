package com.example.gateway.codec;

import com.example.common.constant.Protocol;
import com.example.common.dto.gateway.BizDTO;
import com.sun.corba.se.pept.transport.ByteBufferPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * created 5/8/2021 10:46 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
public class Encoder extends MessageToMessageEncoder<BizDTO> {
    // protected void encode(ChannelHandlerContext channelHandlerContext, BizDTO bizDTO, ByteBuf byteBuf) throws Exception {
    // }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BizDTO bizDTO, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer(Protocol.total + bizDTO.getBody().length);
        buffer.writeInt(bizDTO.getBody().length + Protocol.total)
                .writeInt(bizDTO.getCommand())
                .writeInt(0)
                .writeLong(0)
                .writeBytes(bizDTO.getBody());
        log.info("write message to client: {}", new String(bizDTO.getBody()));
        list.add(buffer);
        //ReferenceCountUtil.release(buffer);
    }
}
