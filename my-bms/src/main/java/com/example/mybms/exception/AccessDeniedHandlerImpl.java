package com.example.mybms.exception;

import com.alibaba.fastjson.JSON;
import com.example.mybms.response.ResultMsg;
import com.example.mybms.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultMsg result = new ResultMsg(HttpStatus.FORBIDDEN.value(), "无权限访问",null);
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
