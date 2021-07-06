package com.example.onen.dao;

import com.example.onen.model.UserInfo;
import com.example.onen.util.MD5Util;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired(required=false)
    private UserMapper userInfoMapper;
    @Autowired
    private MD5Util md5;
    @Test
    void login() throws UnsupportedEncodingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("onen");

        userInfo.setPassword(md5.getMD5("123"));
        UserInfo ret = userInfoMapper.login(userInfo);
        System.out.println(ret);
    }
}