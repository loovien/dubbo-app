package com.example.api.user;


import com.example.common.response.user.UserDTO;

/**
 * created 4/21/2021 5:29 PM
 *
 * @author luowen <loovien@163.com>
 */
public interface UserService {
    UserDTO getUserById(Integer id);
}
