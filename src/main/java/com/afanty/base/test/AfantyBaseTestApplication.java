package com.afanty.base.test;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @author yejx
 * @date 2021-05-20
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.afanty.base.test")
public class AfantyBaseTestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfantyBaseTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AfantyBaseTestApplication.class, args);
        LOGGER.info("已启动");
    }

}
