package com.example.api.user;

import com.example.common.request.user.LoginDTO;
import com.example.common.response.user.UserDTO;

public interface AuthService {
    UserDTO authenticate(LoginDTO loginDTO);
}
