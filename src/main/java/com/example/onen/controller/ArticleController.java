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
import java.util.HashMap;
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
}
