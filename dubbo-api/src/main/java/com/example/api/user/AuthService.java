package com.example.api.user;

import com.example.common.dto.user.LoginDTO;
import com.example.common.dto.user.UserDTO;

public interface AuthService {
    UserDTO authenticate(LoginDTO loginDTO);
}
