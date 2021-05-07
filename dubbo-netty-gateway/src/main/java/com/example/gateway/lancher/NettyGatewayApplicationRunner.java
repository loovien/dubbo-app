package com.example.gateway.lancher;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.Inet6Address;
import java.net.InetAddress;

@Slf4j
@Component
public class NettyGatewayApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        NioEventLoopGroup acceptor = new NioEventLoopGroup(1);
        NioEventLoopGroup handler = new NioEventLoopGroup(10);
        ChannelFuture listener = new ServerBootstrap().group(acceptor, handler)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        log.info("receive from data:");
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter());
                    }
                }).option(ChannelOption.SO_BACKLOG, 100)
                .bind("127.0.0.1", 5564);
        log.info("netty server listen at: 127.0.0.1:5564");
        ChannelFuture channelFuture = listener.sync();
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (Exception exception) {
            acceptor.shutdownGracefully();
            handler.shutdownGracefully();
        }
    }
}
