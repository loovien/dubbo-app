package com.example.web.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * created 5/6/2021 12:33 PM
 *
 * @author luowen <loovien@163.com>
 */
public class UIDUtil {
    public static Integer getId() {
        return (Integer) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                .getAttribute("uid", RequestAttributes.SCOPE_REQUEST);
    }
}
