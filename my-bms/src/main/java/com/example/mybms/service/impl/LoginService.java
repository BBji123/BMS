package com.example.mybms.service.impl;

import com.example.mybms.entity.LoginUser;
import com.example.mybms.entity.SysUser;
import com.example.mybms.response.ResultMsg;
import com.example.mybms.utils.JwtUtil;
import com.example.mybms.utils.RedisCache;
import com.example.mybms.service.ILoginService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResultMsg login(SysUser user) {
        //1. 将SysUser封装成Authentication接口的实现类: UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword());
        //2. 调用AuthenticationManager.authentic()进行用户认证
        Authentication authentication = authenticationManager.authenticate(token);
        //认证未通过抛出异常
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }
        //3. 认证通过返回Authentication对象
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getSysUser().getId().toString();
        //将userid封装成jwt
        String jwt = JwtUtil.createJWT(userid);
        //5. 将用户完整信息存入Redis
        Map<String ,String> map = new HashMap<>();
        map.put("token",jwt);
        map.put("username",loginUser.getUsername());
        redisCache.setCacheObject("login:"+userid,loginUser);
        //将jwt封装成ResultMsg返回
        return new ResultMsg(200,"登录成功",map);
    }

    @Override
    public ResultMsg logout() {
        //1. 获取Authentication
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //2. 通过Authentication.getPrincipal()获取LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //3. 删除Redis中的用户信息
        Long userid = loginUser.getSysUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResultMsg(200,"注销成功",null);
    }

}
