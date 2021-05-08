package com.example.common.dto.gateway;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created 5/8/2021 11:01 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
public class BizDTO implements Serializable {
    private int length;

    private int command;

    private int version;

    private long reserved;

    private byte[] body;
}
