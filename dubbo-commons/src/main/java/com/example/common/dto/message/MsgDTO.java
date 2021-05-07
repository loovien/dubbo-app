package com.example.common.dto.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * created 5/7/2021 10:58 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
public class MsgDTO implements Serializable {

    private Integer sender;

    private Integer receiver;

    private String content;

    private String senderName;

    private String receiverName;

    private Date createdDate;

}
