package com.example.onen.dao;

import com.example.onen.model.ArticleInfo;
import com.example.onen.model.UserInfo;
import com.example.onen.model.UserShow;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ArticleMapper {
    public int addArticle(ArticleInfo article);
    ArrayList<UserInfo> getArticles(Integer uid);

    ArticleInfo getArticleById(Integer id);

    int addRCount(int id);

    int deleteArt(int id);

    int updateArt(ArticleInfo articleInfo);

    UserShow getRcount(int id);
}
