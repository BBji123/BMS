package com.example.mybms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybms.entity.User;
import com.example.mybms.mapper.SysUserMapper;
import com.example.mybms.response.ResultMsg;
import com.example.mybms.service.impl.UserServiceImpl;
import org.apache.ibatis.executor.loader.ResultLoaderMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 * RESTful 风格api
 * @author bbji
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/mybms/user")
public class UserController {
   @Autowired
   private UserServiceImpl userService;

   @Autowired
   private SysUserMapper sysUserMapper;

   /**
    * 查
    * @param id
    */
   @PreAuthorize("@myExpression.hasAuthority('test')")
   @GetMapping("/{id}")
   public ResultMsg<User> getUserById(@PathVariable int id){
      User user = userService.getById(id);
      if(Objects.isNull(user))
         return new ResultMsg<>(200,"do not find user that id = "+id,null);
      ResultMsg<User> resultMsg = new ResultMsg<>();
      resultMsg.success(user);
      return resultMsg;
   }

   /**
    * 增
    * @param user
    * @return
    */
   @PreAuthorize("@myExpression.hasAuthority('insert')")
   @PostMapping(consumes = "application/json")
   public ResultMsg addUser(@RequestBody User user){
      int result = userService.add(user);
      ResultMsg resultMsg = new ResultMsg();
      if(result>0)
            resultMsg.success(null);
      else resultMsg.fail();
      return resultMsg;
   }

   /**
    * 改
    * @param user
    * @return
    */
   @PreAuthorize("@myExpression.hasAuthority('update')")
   @PutMapping(consumes = "application/json")
   public ResultMsg updateUser(@RequestBody User user){
      int result = userService.modifyUser(user);
      ResultMsg resultMsg = new ResultMsg();
      if(result>0)
         resultMsg.success(null);
      else resultMsg.fail();
      return resultMsg;
   }

   /**
    * 删
    * @param id
    * @return
    */
   @PreAuthorize("@myExpression.hasAuthority('delete')")
   @DeleteMapping("/{id}")
   public ResultMsg deleteUser(@PathVariable int id){
      int result = userService.deleteById(id);
      ResultMsg resultMsg = new ResultMsg();
      if(result>0)
         resultMsg.success(null);
      else resultMsg.fail();
      return resultMsg;
   }

   @PreAuthorize("@myExpression.hasAuthority('select')")
   @GetMapping
   public ResultMsg<Page<User>> searchByName(@RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "") String key){
      Page<User> page = userService.findPage(pageSize,pageNum,key);
      ResultMsg<Page<User>> resultMsg = new ResultMsg<>();
      resultMsg.success(page);
      return resultMsg;
   }



}