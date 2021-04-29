package com.example.dubbo.service;

import com.example.dubbo.api.UserService;
import com.example.dubbo.model.UserDTO;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * created 4/21/2021 5:55 PM
 *
 * @author luowen <loovien@163.com>
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getUserById(Integer id) {
        return new UserDTO().setId(id).setName("luowen");
    }

}
