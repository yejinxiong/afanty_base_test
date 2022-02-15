package com.afanty.base.test.common.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * Swagger2接口文档引擎 配置类
 * Swagger2访问地址：http://127.0.0.1:9000/afanty/base/test/swagger-ui.html
 * knife4j访问地址：http://127.0.0.1:9000/afanty/base/test/doc.html
 * </p>
 *
 * @author yejx
 * @date 2020/5/5
 */
@Configuration
public class SwaggerConfig {

    private static final String VERSION = "1.0.0";

    @Value("${springfox.swagger2.enabled}")
    private Boolean swagger2Enabled;

    /**
     * 说明：这里通过.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))标明给加上@Api注解的类自动生成接口文档
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger2Enabled)
                .groupName("Afanty")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("阿凡提基础测试项目-接口文档")
                .description("阿凡提基础测试项目-接口文档")
                .termsOfServiceUrl("http://www.afanty.com")
                .version(VERSION)
                .build();
    }

}
