package com.example.mybms.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.mybms.entity.LoginUser;
import com.example.mybms.response.ResultMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;

/**
 * 自动填充配置类
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //获取Authentication
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //通过Authentication.getPrincipal()获取LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //自动填充的系统用户名
        String sysUserName  = loginUser.getUsername();
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 1);
        this.strictInsertFill(metaObject, "createBy",String.class, sysUserName);
        this.strictInsertFill(metaObject, "updateBy",String.class, sysUserName);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //获取Authentication
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //通过Authentication.getPrincipal()获取LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String sysUserName  = loginUser.getUsername();
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "updateBy",String.class, sysUserName);

    }
}