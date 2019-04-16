package com.frlz.util;

/**
 * @program: frlz
 * @description: 自定义config
 * @author: cz
 * @date: 2019-04-15 15:14
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.frlz.controller"))// 选择那些路径和api会生成document
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(userInfo());
    }



    private ApiInfo userInfo() {
        ApiInfo apiInfo = new ApiInfo("用户相关接口",//大标题
                "用户有关的接口，包括增加删除用户",//小标题
                "0.1",//版本
                "苏州",
                new Contact("cz", "", "b02241@163.com"),// 作者
                "方融量子信息科技有限公司",//链接显示文字
                "www.frlz.ltd"//网站链接
        );
        return apiInfo;
    }

}

