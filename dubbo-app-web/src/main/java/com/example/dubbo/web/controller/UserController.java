package com.example.dubbo.web.controller;

import com.example.dubbo.api.UserService;
import com.example.dubbo.model.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created 4/21/2021 5:43 PM
 *
 * @author luowen <loovien@163.com>
 */
@RestController
public class UserController {

    @DubboReference(version = "1.0.0", retries = 2)
    private UserService userService;

    @GetMapping()
    public UserDTO getUserById(@RequestParam(name = "id", defaultValue = "1") Integer id) {
        return userService.getUserById(id);
    }
}
