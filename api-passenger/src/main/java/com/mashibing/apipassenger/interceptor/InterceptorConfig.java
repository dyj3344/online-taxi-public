package com.mashibing.apipassenger.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：53527
 * @title ：InterceptorConfig
 * @date ：Created in 2023/5/11 9:38
 * @description：<TODO description class purpose>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //将拦截器先放到bean里面交给bean管理,原来是通过new的方式不会去把注入拦截器中的方法实例化
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
        //拦截路径
                .addPathPatterns("/**")
        //不拦截路径
                .excludePathPatterns("/noAuthTest")
        .excludePathPatterns("/verification-code")
        .excludePathPatterns("/verification-code-check")
        .excludePathPatterns("/token-refresh");
    }
}
