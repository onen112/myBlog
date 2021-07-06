package com.example.onen.dao;

import com.example.onen.model.ArticleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleMapperTest {

    @Autowired(required=false)
    ArticleMapper articleMapper;
    @Test
    void addArticle() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("标题");
        articleInfo.setContent("12323wew213123ss");
        articleInfo.setUid(1);

        System.out.println(articleMapper.addArticle(articleInfo));
    }
}