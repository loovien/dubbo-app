package com.example.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * created 5/7/2021 11:03 AM
 *
 * @author luowen <loovien@163.com>
 */
@Data
@Accessors(chain = true)
public class PageResp<T> implements Serializable {
    private long cnt;

    private List<T> list;
}
