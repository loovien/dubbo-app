package com.example.service;

import com.example.common.request.user.LoginDTO;
import com.example.dao.UsersRepository;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.example.dao")
class DubboUserServiceApplicationTests {

    @Autowired
    UsersRepository usersRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void userLogin() {
        LoginDTO loginDTO = new LoginDTO().setUsername("luowen").setPassword("admin");
        Object o = usersRepository.fetchUserBy(loginDTO);
        System.out.println(o);
    }

    @Test
    void getUserBy() {
        Object o = usersRepository.selectById(1);
        System.out.println(o);
    }

}
