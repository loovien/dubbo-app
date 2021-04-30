package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.request.user.LoginDTO;

public interface UsersRepository<UsersDO> extends BaseMapper<UsersDO> {

    UsersDO fetchUserBy(LoginDTO loginDTO);
}
