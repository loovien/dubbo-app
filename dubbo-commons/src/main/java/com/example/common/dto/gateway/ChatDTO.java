package com.example.common.dto.gateway;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * created 5/8/2021 3:44 PM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
public class ChatDTO implements Serializable {

    private Integer sender;

    private Integer receiver;

    private String content;

    private Date createdDate;

}
