package com.example.mybms.exception;

import com.alibaba.fastjson.JSON;
import com.example.mybms.response.ResultMsg;
import com.example.mybms.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //认证失败返回的数据
        ResultMsg result = new ResultMsg(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误",null);
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
