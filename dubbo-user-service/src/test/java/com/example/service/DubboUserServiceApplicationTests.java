package com.example.service;

import com.example.common.model.UsersDO;
import com.example.common.request.user.LoginDTO;
import com.example.dao.UsersRepository;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DubboUserServiceApplicationTests {

    @Autowired
    UsersRepository<UsersDO> usersRepository;

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
        List<UsersDO> usersDOS = usersRepository.selectList(null);
        System.out.println(usersDOS);
    }

}
