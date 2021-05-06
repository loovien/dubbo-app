package com.example.common.annotation;

import java.lang.annotation.*;

/**
 * created 5/6/2021 11:31 AM
 *
 * @author luowen <loovien@163.com>
 */
@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UIDInject {
}
