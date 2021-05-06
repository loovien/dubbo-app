package com.example.common.annotation;

import java.lang.annotation.*;

/**
 * created 5/6/2021 11:30 AM
 *
 * @author luowen <loovien@163.com>
 */
@Inherited
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    boolean value() default true;
}
