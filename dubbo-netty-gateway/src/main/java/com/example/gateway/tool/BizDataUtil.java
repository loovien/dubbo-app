package com.example.gateway.tool;

import com.example.common.constant.Protocol;
import com.example.common.dto.gateway.BizDTO;

import java.nio.charset.StandardCharsets;

/**
 * created 5/8/2021 3:51 PM
 *
 * @author luowen <loovien@163.com>
 */

public class BizDataUtil {

    public static BizDTO builder(byte[] data) {
        return new BizDTO().setLength(Protocol.total + data.length)
                .setCommand(10)
                .setBody(data);
    }
}
