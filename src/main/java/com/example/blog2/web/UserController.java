package com.example.blog2.web;

import com.example.blog2.po.Result;
import com.example.blog2.po.StatusCode;
import com.example.blog2.po.User;
import com.example.blog2.service.UserService;
import com.example.blog2.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping( "/login")
    //获取用户名的键值对是"user":User
    public Result login(@RequestBody Map<String, User> para) {
        User u = para.get("user");
        User user = userService.checkUser(u.getUsername(), u.getPassword());
        if (user != null) {
            String token = TokenUtil.sign(user);
            Map<String, Object> info = new HashMap<>();
            user.setLoginProvince(u.getLoginProvince());
            user.setLoginCity(u.getLoginCity());
            user.setLoginLat(u.getLoginLat());
            user.setLoginLng(u.getLoginLng());
            user.setLastLoginTime(new Date());
            User newUser = userService.save(user);
            info.put("user", newUser);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "管理员登录成功", info);
        } else {
            return new Result(true, StatusCode.ERROR, "管理员登录失败", null);
        }
    }

    @PostMapping(value = "/registor")
    public Result post(@RequestBody Map<String, User> para)  {
        User u = para.get("user");
        if (u != null) {
            User user = userService.save(u);
            log.info(user + "");
            String token = TokenUtil.sign(user);
            Map<String, Object> info = new HashMap<>();
            info.put("user", user);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "注册普通用户", info);
        } else {
            return new Result(true, StatusCode.ERROR, "注册普通用户失败", null);
        }
    }

    @GetMapping(value = "/users")
    public Result get(){
        System.out.println(userService.listUser());
        return new Result(true, StatusCode.OK, "获取用户列表成功", userService.listUser());
    }
}
