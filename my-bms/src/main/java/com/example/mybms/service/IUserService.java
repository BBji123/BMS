package com.example.mybms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bbji
 * @since 2022-01-18
 */
public interface IUserService extends IService<User> {
    /**
     * 根据id查找user
     * @param id
     * @return
     */
    public User getById(int id);

    /**
     * 修改用户信息
     * @param user
     */
    public int modifyUser(User user);

    /**
     * 按id删除user
     * @param id
     */
    public int deleteById(int id);

    /**
     * 添加用户
     * @param user
     */
    public int add(User user);

    /**
     * 分页查询
     * @return
     */
    public Page<User> findPage(int pageSize,int pageNum,String key);


}
