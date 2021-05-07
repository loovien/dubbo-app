package com.example.service;

import com.example.api.user.AuthService;
import com.example.common.model.UsersDO;
import com.example.common.dto.user.LoginDTO;
import com.example.common.dto.user.UserDTO;
import com.example.common.util.JwtUtils;
import com.example.dao.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService(version = "1.0.0", retries = 2, loadbalance = "random")
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;

    public AuthServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDTO authenticate(LoginDTO loginDTO) {
        UsersDO usersDO = usersRepository.fetchUserBy(loginDTO);
        if (usersDO == null) {
            return null;
        }
        if (!usersDO.getPassword().equals(loginDTO.getPassword())) {
            return null;
        }
        String token = JwtUtils.token(usersDO.getId());
        return new UserDTO().setId(usersDO.getId())
                .setName(usersDO.getUsername())
                .setToken(token);
    }
}
