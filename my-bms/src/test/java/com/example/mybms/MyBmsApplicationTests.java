package com.example.mybms;

import com.example.mybms.entity.User;
import com.example.mybms.mapper.SysUserMapper;
import com.example.mybms.mapper.UserMapper;
import com.example.mybms.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MyBmsApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SysUserMapper sysUserMapper;



    @Test
    void contextLoads() {
    }

    @Test
    void testMapper(){
        List<String> auth = sysUserMapper.getAccess(1001L);
        System.out.println(auth);
    }

    @Test
    void testService(){
        User user = userService.getById(1001);
        System.out.println(user);
    }

}
