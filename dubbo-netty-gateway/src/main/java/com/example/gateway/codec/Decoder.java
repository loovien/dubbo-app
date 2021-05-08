package com.example.gateway.codec;

import com.example.common.constant.Protocol;
import com.example.common.dto.gateway.BizDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * created 5/8/2021 10:49 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        String remote = channelHandlerContext.channel().remoteAddress().toString();
        log.info("client remote: {} connected", remote);
        int length = byteBuf.readInt();
        if (length <= 0) {
            channelHandlerContext.channel().close();
            throw new RuntimeException("connection package invalid: " + remote);
        }
        if (byteBuf.readableBytes() < (length - Protocol.headLength)) {
            byteBuf.resetReaderIndex();
            return;
        }
        BizDTO bizDTO = new BizDTO().setLength(length)
                .setCommand(byteBuf.readInt())
                .setVersion(byteBuf.readInt())
                .setReserved(byteBuf.readLong());
        byte[] data = new byte[length - Protocol.total];
        byteBuf.readBytes(data);
        bizDTO.setBody(data);
        log.info("receive id: {} packet: {}", bizDTO.getCommand(), new String(bizDTO.getBody()));
        list.add(bizDTO);
        // ReferenceCountUtil.release(byteBuf);
    }
}
