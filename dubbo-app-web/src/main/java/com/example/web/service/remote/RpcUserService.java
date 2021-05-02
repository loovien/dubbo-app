package com.example.web.service.remote;

import com.example.api.user.AuthService;
import com.example.api.user.UserService;
import com.example.common.model.UsersDO;
import com.example.common.request.user.LoginDTO;
import com.example.common.response.user.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
public class RpcUserService {

    @DubboReference(version = "1.0.0", retries = 2)
    private UserService userService;

    @DubboReference(version = "1.0.0", retries = 2)
    private AuthService authService;

    public UserDTO getUserBy(Integer id) {
        return userService.getUserById(id);
    }

    public UserDTO userLogin(LoginDTO loginDTO) {
        return authService.authenticate(loginDTO);
    }
}
