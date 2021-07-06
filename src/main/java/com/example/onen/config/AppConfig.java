package com.example.onen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 拦截所有的接口
                .excludePathPatterns("/user/login") // 不拦截登录接口
                .excludePathPatterns("/user/sigin") // 不拦截注册接口
                .excludePathPatterns("/login.html") // 不拦截登录页面
                .excludePathPatterns("/sigin.html") // 不拦截注册页面
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.png")
        ;

    }
}
