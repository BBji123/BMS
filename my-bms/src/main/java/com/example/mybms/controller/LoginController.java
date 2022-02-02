package com.example.mybms.controller;

import com.example.mybms.entity.SysUser;
import com.example.mybms.response.ResultMsg;
import com.example.mybms.service.ILoginService;
import com.example.mybms.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mybms/user")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResultMsg login(@RequestBody SysUser user){
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    public ResultMsg logout(){
        return loginService.logout();
    }


}
