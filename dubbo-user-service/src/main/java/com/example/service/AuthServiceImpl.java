package com.example.service;

import com.example.api.user.AuthService;
import com.example.common.model.UsersDO;
import com.example.common.request.user.LoginDTO;
import com.example.common.response.user.UserDTO;
import com.example.dao.UsersRepository;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", retries = 2, loadbalance = "random")
public class AuthServiceImpl implements AuthService {
    private final UsersRepository<UsersDO> usersRepository;

    public AuthServiceImpl(UsersRepository<UsersDO> usersRepository) {
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
        return new UserDTO().setId(usersDO.getId()).setName(usersDO.getUsername());
    }
}
