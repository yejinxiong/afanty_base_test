package com.afanty.base.test;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
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
@Slf4j
@EnableSwagger2
@MapperScan(basePackages = "com.afanty.base.test")
public class AfantyBaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfantyBaseTestApplication.class, args);
        log.info("已启动");
    }

}
