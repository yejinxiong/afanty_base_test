package com.afanty.base.test.common.config;

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
 * 访问地址：http://127.0.0.1:9000/mp/ac/system/swagger-ui.html
 * </p>
 *
 * @author yejx
 * @date 2020/5/5
 */
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.afanty.mp.ac.system.business"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理平台-权限中心-系统 API 文档")
                .description("管理平台-权限中心-系统 API 网关接口，http://127.0.0.1:9000")
                .termsOfServiceUrl("http://127.0.0.1:9000")
                .version("1.0.0")
                .build();
    }

}
