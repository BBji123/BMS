package com.example.mybms.service;

import com.example.mybms.entity.SysUser;

import java.util.List;

public interface IAuthorityService {
    public List<String> getAccessBySysUser(SysUser user);
}
