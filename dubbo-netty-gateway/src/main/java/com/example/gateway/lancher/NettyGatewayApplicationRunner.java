package com.example.gateway.lancher;

import com.example.gateway.codec.Decoder;
import com.example.gateway.codec.Encoder;
import com.example.gateway.config.AppConfigs;
import com.example.gateway.handler.DispatcherHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyGatewayApplicationRunner implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    private final AppConfigs appConfigs;

    public NettyGatewayApplicationRunner(ApplicationContext applicationContext, AppConfigs appConfigs) {
        this.applicationContext = applicationContext;
        this.appConfigs = appConfigs;
    }


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
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new Decoder());
                        pipeline.addLast(new Encoder());
                        pipeline.addLast(new DispatcherHandler(applicationContext));
                    }
                }).option(ChannelOption.SO_BACKLOG, 100)
                .bind(appConfigs.getAddress(), appConfigs.getPort());
        log.info("netty server listen at: {}:{}", appConfigs.getAddress(), appConfigs.getPort());
        ChannelFuture channelFuture = listener.sync();
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (Exception exception) {
            acceptor.shutdownGracefully();
            handler.shutdownGracefully();
        }
    }
}
