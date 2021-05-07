package com.example.service;

import com.example.constant.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class RedisMsgService {
    private final StringRedisTemplate stringRedisTemplate;

    public RedisMsgService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void publish(Object message) {
        stringRedisTemplate.convertAndSend(RedisKey.chatTopicKey, message);
    }

    // @Scheduled(cron = "*/10 * * * * *")
    public void msgTest() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().format(dateTimeFormatter);
        String msg = "hello: " + datetime;
        log.info("send message: {}", msg);
        publish(msg);
    }
}
