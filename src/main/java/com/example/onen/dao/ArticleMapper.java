package com.example.onen.dao;

import com.example.onen.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    public int addArticle(ArticleInfo article);
}
