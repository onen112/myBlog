package com.example.onen.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private String photo;
    private Date createtime;
    private Date updatetime;
    private int state;
    private String github;
}
