package com.example.web.interceptor;

import com.example.common.annotation.LoginRequired;
import com.example.common.annotation.UIDInject;
import com.example.common.util.JwtUtils;
import com.example.common.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created 5/6/2021 11:33 AM
 *
 * @author luowen <loovien@163.com>
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        HandlerMethod handler1 = (HandlerMethod) handler;
        UIDInject uidInject = handler1.getMethodAnnotation(UIDInject.class);
        if (uidInject != null) {
            try {
                Integer uid = JwtUtils.getId(authorization);
                request.setAttribute("uid", uid);
            } catch (Exception e) {
                log.error("uid inject with token: {} failure, err: {}", authorization, e.getMessage());
            }
            return true;
        }
        LoginRequired loginRequired = handler1.getMethodAnnotation(LoginRequired.class);
        if (loginRequired == null || !loginRequired.value()) {
            return true;
        }
        if (authorization == null || StringUtils.isEmpty(authorization)) {
            throw new NotLoginException();
        }
        Integer uid = JwtUtils.getId(authorization);
        request.setAttribute("uid", uid);
        return true;
    }
}
