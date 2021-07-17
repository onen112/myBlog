package com.example.onen.controller;

import com.example.onen.model.ArticleInfo;
import com.example.onen.model.UserInfo;
import com.example.onen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/addArticle")
    public Object addArticle(@RequestBody ArticleInfo articleInfo, HttpServletRequest request){

        int state = -1;
        String msg = "未知错误";
        HttpSession session = request.getSession(false);
        if(articleInfo == null && articleInfo.getTitle()  == null || articleInfo.getTitle().equals("") || articleInfo.getContent() == null || articleInfo.getContent().equals("")){
            state = -2;
            msg = "文章标题和文章正文部分不能为空";
        }else if(session == null){
            state = -3;
            msg = "用户暂未登录，无法提交";
        }else if(session.getAttribute("user") != null){
            UserInfo user = (UserInfo)session.getAttribute("user");
            articleInfo.setUid(user.getId());
            if(articleService.addArticle(articleInfo)){
                msg = "文章上传成功！";
                state = 1;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        return map;
    }

    @RequestMapping("/getArticles")
    public Object getArticleList(HttpServletRequest request){
        List<UserInfo> articles = null;
        int state = -1;
        String msg = "未知错误";
        HttpSession session = request.getSession();
        if(session != null && session.getAttribute("user") != null){
            UserInfo user = (UserInfo)session.getAttribute("user");
            int id = user.getId();

           articles = articleService.getArticles(id);
           state = 1;
           msg = "查询成功";
        }else{
            state = 0;
            msg = "查询失败，用户未登录";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("article",articles);
        map.put("msg",msg);
        return map;
    }
    @RequestMapping("/getContent")
    public Object getContentById(Integer id){
        int state = -1;
        String msg = "未知错误";
        ArticleInfo article = null;
        if(id != null){
          article = articleService.getArticleById(id);
          if(article != null){
              state = 1;
              msg = "查询成功";   
          }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        map.put("article",article);
        return map;
    }
    @RequestMapping("/deleteArt")
    public Object deleteArt(Integer id){
        int state = -1;
        String msg = "未知错误";

        if(articleService.deleteArt(id)){
            state = 1;
            msg = "删除成功!";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        return map;
    }
    @PostMapping("/updateArt")
    public Object updateArt(@RequestBody ArticleInfo articleInfo){
        int state = -1;
        String msg = "未知错误";

        if(articleService.updateArt(articleInfo)){
            state = 1;
            msg = "更新成功";

        }else{
            state = 0;
            msg = "更新出错，请稍后重试";
        }
        Map<String ,Object> map = new HashMap<>();
        map.put("state",state);
        map.put("msg",msg);
        return map;
    }
}
