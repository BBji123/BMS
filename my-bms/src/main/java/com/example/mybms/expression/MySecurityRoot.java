package com.example.mybms.expression;

import com.example.mybms.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 自定义权限校验方式
 */
@Component("myExpression")
public class MySecurityRoot {
    public boolean hasAuthority(String authority){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) authentication.getPrincipal();
        List<String> authorities = user.getAuthentication();
        return authorities.contains(authority);
    }
}