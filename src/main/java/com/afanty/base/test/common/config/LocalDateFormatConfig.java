package com.afanty.base.test.common.config;

import com.afanty.base.test.common.contanst.LocalDateConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * LocalDateTime 日期格式化全局配置
 * </p>
 *
 * @author yejx
 * @date 2021/5/5
 */
@Configuration
public class LocalDateFormatConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(LocalDateConstant.FORMATTER_DATE_YYYY_MM_DD_HH_MM_SS));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(LocalDateConstant.FORMATTER_DATE_YYYY_MM_DD));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(LocalDateConstant.FORMATTER_DATE_HH_MM_SS));
        om.registerModule(javaTimeModule);
        return om;
    }
}
