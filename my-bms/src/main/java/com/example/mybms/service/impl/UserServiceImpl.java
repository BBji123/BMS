package com.example.mybms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybms.entity.User;
import com.example.mybms.mapper.UserMapper;
import com.example.mybms.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bbji
 * @since 2022-01-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int modifyUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Page<User> findPage(int pageSize, int pageNum, String key) {
        Page<User> page = new Page<>(pageNum,pageSize);
        //如果key参数为空 查询所有数据
        if(key.equals("")){
            return userMapper.selectPage(page,new QueryWrapper<User>().isNotNull("id"));
        }
        else{
            return userMapper.selectPage(page, Wrappers.<User>lambdaQuery().like(User::getUsername,key));
        }
    }
}
