package com.example.common.response.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created 4/21/2021 5:29 PM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

    private Integer id;

    private String name;
}
