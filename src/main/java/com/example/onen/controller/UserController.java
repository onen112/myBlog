package com.example.onen.controller;


import com.example.onen.model.UserInfo;
import com.example.onen.service.UserService;
import com.example.onen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    MD5Util md5;
    @PostMapping("/login")
    public Object login(@RequestBody UserInfo user) throws UnsupportedEncodingException {
        int state = -1;
        String msg = "未知错误";
        Object data;
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String,Object> map = new HashMap<>();
        if(username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            state = 0;
            msg = "登录失败用户名和密码不能为空";
        }else{
            user.setPassword(md5.getMD5(password));
            boolean ret = userService.login(user);
            if(ret){
                state = 1;
                msg = "登录成功！";
            }else{
                state = 0;
                msg = "用户名或密码错误";
            }
            map.put("state",state);
            map.put("msg",msg);
        }
        return map;
    }

    @PostMapping("sigin")
    public Object sigin(@RequestBody UserInfo user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        int state = -1;
        String msg = "未知错误";
        Object data;
        String username = user.getUsername();
        String password = user.getPassword();
        if(username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            state = 0;
            msg = "注册失败用户名和密码不能为空";
        }else if(userService.isSigin(username)) {
            state = -2;
            msg = "注册失败，当前用户名已经存在";
        }else{
                user.setPassword(md5.getMD5(password));
                user.setCreatetime(new Date());
                if(userService.sigin(user)){
                    state = 1;
                    msg = "注册成功";
                }
            }
        Map<String,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        return map;

    }
}
