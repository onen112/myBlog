package com.example.onen.service;

import com.example.onen.dao.ArticleMapper;
import com.example.onen.model.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired(required=false)
    ArticleMapper articleMapper;
    public boolean addArticle(ArticleInfo articleInfo){
        int i = articleMapper.addArticle(articleInfo);
        return i>0;
    }
}
