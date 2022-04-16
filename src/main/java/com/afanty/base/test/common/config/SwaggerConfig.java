package com.afanty.base.test.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * Swagger2接口文档引擎 配置类
 * Swagger2访问地址：http://127.0.0.1:9000/afanty/base/test/swagger-ui.html
 * knife4j访问地址：http://127.0.0.1:9000/afanty/base/test/doc.html
 * </p>
 * |-- @ApiImplicitParam
 *     |-- paramType：代表参数应该放在请求的什么地方（配合allowMultiple = true使用，可以传递数组）
 *         |-- header   放在请求头。请求参数的获取：@RequestHeader(代码中接收注解)
 *         |-- query    用于get请求的参数拼接。请求参数的获取：@RequestParam(代码中接收注解)
 *         |-- path    （用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
 *         |-- body     放在请求体。请求参数的获取：@RequestBody(代码中接收注解)，可用于接收数组
 *         |-- form    （不常用）
 *     |-- dataType： 代表请求参数类型（更多类型往下看：io.swagger.models.properties）
 *         |-- string
 *         |-- int
 *         |-- long
 *         |-- Map
 *         |-- User
 *     |--allowMultiple：表示是否为数组格式
 *         |-- false    否（默认）
 *         |-- true    是
 *
 *
 * @author yejx
 * @date 2020/5/5
 */
@Configuration
//@EnableSwagger2
@EnableKnife4j
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
                // 是否开启swagger
                .enable(swagger2Enabled)
                .groupName("Default")
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.afanty.*"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean("FTP")
    public Docket createRestApiFTP() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 是否开启swagger
                .enable(swagger2Enabled)
                .groupName("FTP")
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.afanty.base.test.ftp.rest"))
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

    /**
     * dataType
     */
    private static final Map<Type, String> dataType = ImmutableMap.<Type, String>builder()
            .put(Long.TYPE, "long")
            .put(Short.TYPE, "int")
            .put(Integer.TYPE, "int")
            .put(Double.TYPE, "double")
            .put(Float.TYPE, "float")
            .put(Byte.TYPE, "byte")
            .put(Boolean.TYPE, "boolean")
            .put(Character.TYPE, "string")
            .put(Date.class, "date-time")
            .put(java.sql.Date.class, "date")
            .put(String.class, "string")
            .put(Object.class, "object")
            .put(Long.class, "long")
            .put(Integer.class, "int")
            .put(Short.class, "int")
            .put(Double.class, "double")
            .put(Float.class, "float")
            .put(Boolean.class, "boolean")
            .put(Byte.class, "byte")
            .put(BigDecimal.class, "bigdecimal")
            .put(BigInteger.class, "biginteger")
            .put(Currency.class, "string")
            .put(UUID.class, "uuid")
            .put(MultipartFile.class, "__file") // 或者“file”
            .build();

}
