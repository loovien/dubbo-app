package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.api.message.MessageService;
import com.example.common.dto.PageResp;
import com.example.common.dto.message.MsgDTO;
import com.example.common.model.MessagesDO;
import com.example.common.constant.RedisKey;
import com.example.dao.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created 5/7/2021 11:16 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
@DubboService(version = "1.0.0")
public class MessageServiceImpl implements MessageService {

    private final ObjectMapper objectMapper;

    private final MessageRepository messageRepository;

    private final StringRedisTemplate stringRedisTemplate;

    public MessageServiceImpl(ObjectMapper objectMapper, MessageRepository messageRepository, StringRedisTemplate stringRedisTemplate) {
        this.objectMapper = objectMapper;
        this.messageRepository = messageRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public MsgDTO send(MsgDTO msgDTO) {
        MessagesDO messagesDO = new MessagesDO();
        messagesDO.setContent(msgDTO.getContent())
                .setSender(msgDTO.getSender())
                .setReceiver(msgDTO.getReceiver())
                .setCreatedDate(new Date())
                .setUpdatedDate(new Date());
        int recordID = messageRepository.insert(messagesDO);
        log.info("receive message: {} last insert ID: {}", messagesDO, recordID);
        try {
            String data = objectMapper.writeValueAsString(messagesDO);
            stringRedisTemplate.convertAndSend(RedisKey.chatTopicKey, data);
        } catch (JsonProcessingException e) {
            log.error("json serialize messageDO failure, {}", messagesDO);
        }
        return msgDTO;
    }

    @Override
    public PageResp<MsgDTO> getMsgList(Integer uid, Integer page, Integer size) {
        IPage<MessagesDO> listByUid = messageRepository.selectMsgListByUid(new Page<>(page, size), uid);
        PageResp<MsgDTO> msgDTOPageResp = new PageResp<MsgDTO>()
                .setCnt(listByUid.getTotal())
                .setList(new ArrayList<>());
        List<MessagesDO> records = listByUid.getRecords();
        for (MessagesDO record : records) {
            MsgDTO msgDTO = new MsgDTO().setSender(record.getSender())
                    .setReceiver(record.getReceiver())
                    .setContent(record.getContent())
                    .setCreatedDate(record.getCreatedDate());

            msgDTOPageResp.getList().add(msgDTO);
        }
        log.info("query messageDTO list: {}", msgDTOPageResp);
        return msgDTOPageResp;
    }
}
