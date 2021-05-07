package com.example.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * created 5/7/2021 10:56 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
@TableName(value = "messages")
public class MessagesDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sender;

    private Integer receiver;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedDate;
}
