package com.example.onen.model;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private int uid;
    private int rcount;
    private int state;
}
