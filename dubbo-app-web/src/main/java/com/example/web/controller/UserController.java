package com.example.web.controller;

import com.example.api.user.UserService;
import com.example.common.request.user.LoginDTO;
import com.example.common.response.Result;
import com.example.common.response.user.UserDTO;
import com.example.web.service.remote.RpcUserService;
import org.springframework.web.bind.annotation.*;

/**
 * created 4/21/2021 5:43 PM
 *
 * @author luowen <loovien@163.com>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final RpcUserService rpcUserService;

    public UserController(RpcUserService rpcUserService) {
        this.rpcUserService = rpcUserService;
    }

    @GetMapping()
    public Result<UserDTO> getUserById(@RequestParam(name = "id", defaultValue = "1") Integer id) {
        UserDTO userBy = rpcUserService.getUserBy(id);
        if (userBy == null) {
            return Result.wrapErr(-1, "用户记录不存在");
        }
        return Result.wrapOK(userBy);
    }

    @PostMapping("/login")
    public Result<?> userLogin(@RequestBody LoginDTO loginDTO) {
        UserDTO userDTO = rpcUserService.userLogin(loginDTO);
        if (userDTO == null) {
            return Result.wrapErr(-1, "账号或密码错误");
        }
        return Result.wrapOK(userDTO);
    }
}
