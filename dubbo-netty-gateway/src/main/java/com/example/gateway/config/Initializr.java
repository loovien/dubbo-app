package com.example.gateway.config;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created 5/8/2021 3:38 PM
 *
 * @author luowen <loovien@163.com>
 */
@Getter
@Setter
@Configuration(proxyBeanMethods = false)
public class Initializr {
    private final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final Map<String, Channel> sockets = new ConcurrentHashMap<>();
}
