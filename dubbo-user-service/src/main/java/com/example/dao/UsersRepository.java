package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.model.UsersDO;
import com.example.common.request.user.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersRepository extends BaseMapper<UsersDO> {

    UsersDO fetchUserBy(LoginDTO loginDTO);

    // UsersDO selectById(Integer id);

}
