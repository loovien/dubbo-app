package com.example.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * created 5/8/2021 10:35 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@ConfigurationProperties(prefix = "netty")
public class AppConfigs {

    private String address;

    private Integer port;
}
