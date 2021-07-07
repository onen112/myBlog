package com.example.onen.service;

import com.example.onen.dao.ArticleMapper;
import com.example.onen.model.ArticleInfo;
import com.example.onen.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired(required=false)
    ArticleMapper articleMapper;
    public boolean addArticle(ArticleInfo articleInfo){
        int i = articleMapper.addArticle(articleInfo);
        return i>0;
    }

    public List<UserInfo> getArticles(int id){

        return articleMapper.getArticles(id);
    }

    public ArticleInfo getArticleById(int id) {
        ArticleInfo ret = articleMapper.getArticleById(id);
        if(ret == null){
            return null;
        };
        //否则增加阅读量
        articleMapper.addRCount(id);
        return ret;
    }

    public boolean deleteArt(Integer id) {
        return articleMapper.deleteArt(id) > 0;
    }

    public boolean updateArt(ArticleInfo articleInfo) {
        return articleMapper.updateArt(articleInfo) > 0;
    }
}
