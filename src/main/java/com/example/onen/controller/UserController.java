package com.example.onen.controller;


import com.example.onen.model.UserInfo;
import com.example.onen.model.UserShow;
import com.example.onen.service.ArticleService;
import com.example.onen.service.UserService;
import com.example.onen.util.MD5Util;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    ArticleService articleService;
    @Autowired
    MD5Util md5;

    //用户登录接口
    @PostMapping("/login")
    public Object login(@RequestBody UserInfo user, HttpServletRequest request) throws UnsupportedEncodingException {
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
            UserInfo ret = userService.login(user);
            if(ret != null && ret.getUsername().equals(user.getUsername())){
                state = 1;
                msg = "登录成功！";
                HttpSession session = request.getSession();
                session.setAttribute("user",ret);
            }else{
                state = 0;
                msg = "用户名或密码错误";
            }
            map.put("state",state);
            map.put("msg",msg);
        }
        return map;
    }
    //用户注册接口
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

    //获取用户信息
    @RequestMapping("/getUserInfo")
    public Object getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        int state = -1;
        String msg = "未知错误";
        UserShow user = null;
        if(session == null){
            state = 0;
            msg = "用户未登录";
        }else{
            UserInfo userInfo = (UserInfo) session.getAttribute("user");
            if(userInfo != null){
                user = articleService.selectRcount(userInfo.getId());
                user.setUsername(userInfo.getUsername());
                state = 1;
                msg = "查询成功";
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        map.put("user",user);
        return map;
    }

}
