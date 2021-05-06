package com.example.service;

import com.example.api.user.UserService;
import com.example.common.model.UsersDO;
import com.example.common.response.user.UserDTO;
import com.example.dao.UsersRepository;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * created 4/21/2021 5:55 PM
 *
 * @author luowen <loovien@163.com>
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDTO getUserById(Integer id) {
        UsersDO usersDO = usersRepository.selectById(id);
        if (usersDO == null) {
            throw new RuntimeException("id active record not found!");
        }
        return new UserDTO().setId(usersDO.getId()).setName(usersDO.getUsername());
    }

}
