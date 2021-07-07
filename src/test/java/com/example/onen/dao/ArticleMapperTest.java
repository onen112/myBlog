package com.example.onen.dao;

import com.example.onen.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {

    @Autowired(required=false)
    ArticleMapper articleMapper;
    @Test
    void addArticle() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("标题");

        articleInfo.setUid(1);

        System.out.println(articleMapper.addArticle(articleInfo));
    }

    @Test
    void getArticles() {
        ArrayList articles = articleMapper.getArticles(1);
        System.out.println(articles);

    }

    @Test
    void getArticleById() {
        ArticleInfo articleById1 = articleMapper.getArticleById(6);
        System.out.println(articleById1);

    }

    @Test
    void addRCount() {
        articleMapper.addRCount(4);
    }

    @Test
    void deleteArt() {
        int i = articleMapper.deleteArt(1);
        System.out.println(i);
    }

    @Test
    void updateArt() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(3);
        articleInfo.setTitle("只是标题");
        articleInfo.setContent("# 我只是试试 别急");
        System.out.println(articleMapper.updateArt(articleInfo));
    }
}