package com.example.mybms.service;

import com.example.mybms.entity.SysUser;
import com.example.mybms.response.ResultMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录/退出接口
 */
public interface ILoginService {
    /**
     * 登录
     * @param user
     * @return
     */
    public ResultMsg login(SysUser user);

    /**
     * 注销
     * @return
     */
    public ResultMsg logout();

}
