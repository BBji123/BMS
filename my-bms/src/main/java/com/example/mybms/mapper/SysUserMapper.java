package com.example.mybms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybms.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    public List<String> getAccess(@Param("id") long id);
}
