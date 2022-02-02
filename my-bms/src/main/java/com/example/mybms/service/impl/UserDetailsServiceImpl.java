package com.example.mybms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybms.entity.LoginUser;
import com.example.mybms.entity.SysUser;
import com.example.mybms.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getName,username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        //若查询结果为空 抛出异常
        if(Objects.isNull(user)) throw new RuntimeException("用户名错误");
        //List<String> authenticates = new ArrayList<>(Arrays.asList("test"));
        List<String> authenticates = sysUserMapper.getAccess(user.getId());
        //将查询结果封装成UserDetails
        return new LoginUser(user,authenticates);
    }
}
