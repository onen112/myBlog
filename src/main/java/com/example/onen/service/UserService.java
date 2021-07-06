package com.example.onen.service;

import com.example.onen.dao.UserMapper;
import com.example.onen.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired(required=false)
    UserMapper userMapper;

    //用户登录
    public UserInfo login(UserInfo user){

        UserInfo ret = userMapper.login(user);
        System.out.println(ret);
        return ret;
    }

    //用户注册
    public boolean sigin(UserInfo user){
        int ret = userMapper.sigin(user);
        return ret>0;
    }

    //验证用户是否存在
    public boolean isSigin(String username){
        return userMapper.isSigin(username)>0;
    }


}
