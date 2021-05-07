package com.example.web.controller;

import com.example.common.dto.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * created 5/7/2021 10:18 AM
 *
 * @author luowen <loovien@163.com>
 */
@RestController
public class IndexController {

    public Result<?> index() {
        return Result.wrapOK("dubbo-app-web api healthy.");
    }
}
