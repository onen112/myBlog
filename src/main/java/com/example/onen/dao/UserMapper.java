package com.example.onen.dao;

import com.example.onen.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public UserInfo login(UserInfo userInfo);
    public int sigin(UserInfo userInfo);
    public int isSigin(String username);

}
