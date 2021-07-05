package com.example.onen.service;

import com.example.onen.dao.UserMapper;
import com.example.onen.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required=false)
    UserMapper userMapper;

    public boolean login(UserInfo user){
        int ret = userMapper.login(user);
        return ret>0;
    }

    public boolean sigin(UserInfo user){
        int ret = userMapper.sigin(user);
        return ret>0;
    }

    public boolean isSigin(String username){
        return userMapper.isSigin(username)>0;
    }
}
