package com.example.gateway;

import com.example.common.dto.gateway.BizDTO;
import com.example.gateway.codec.Decoder;
import com.example.gateway.codec.Encoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * created 5/8/2021 4:54 PM
 *
 * @author luowen <loovien@163.com>
 */
public class NettyClient {

    @Test
    void connect() throws InterruptedException {

        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        ChannelFuture sync = new Bootstrap().group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();

                        pipeline.addLast(new Encoder())
                                .addLast(new Decoder())
                                .addLast(new SimpleChannelInboundHandler<BizDTO>() {

                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        String body = "{\"token\":\"abc123\"}";
                                        BizDTO bizDTO = new BizDTO().setBody(body.getBytes(StandardCharsets.UTF_8))
                                                .setCommand(1)
                                                .setLength(20 + body.getBytes(StandardCharsets.UTF_8).length);
                                        ctx.channel().writeAndFlush(bizDTO);
                                    }

                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BizDTO bizDTO) throws Exception {
                                        System.out.println("+===================================================+");
                                        System.out.println(bizDTO);
                                        System.out.println("+===================================================+");
                                    }
                                });
                    }
                }).connect("127.0.0.1", 8088).sync();
        sync.channel().closeFuture().sync();
        eventExecutors.shutdownGracefully();

    }

}
