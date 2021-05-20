package com.afanty.base.test;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 * @author yejx
 * @date 2021-05-20
 */
@SpringBootApplication
@Log
@EnableSwagger2
public class AfantyBaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfantyBaseTestApplication.class, args);
        log.info("已启动");
    }

}
