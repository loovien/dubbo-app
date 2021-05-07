package com.example;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.model.MessagesDO;
import com.example.dao.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;

@SpringBootTest
@MapperScan("com.example.dao")
class DubboMessageServiceApplicationTests {

    @Autowired
    MessageRepository messageRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void getMessageByList() {
        Page<MessagesDO> messagesDOPage = new Page<>(1, 10);
        IPage<MessagesDO> messagesDOIPage = messageRepository.selectMsgListByUid(messagesDOPage, 1);
        System.out.println(messagesDOIPage.getTotal() + ":" + messagesDOIPage.getRecords());
    }

}
