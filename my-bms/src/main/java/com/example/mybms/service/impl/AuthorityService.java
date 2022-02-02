package com.example.mybms.service.impl;

import com.example.mybms.entity.SysUser;
import com.example.mybms.mapper.SysUserMapper;
import com.example.mybms.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService implements IAuthorityService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<String> getAccessBySysUser(SysUser user) {
        return sysUserMapper.getAccess(user.getId());
    }

}
