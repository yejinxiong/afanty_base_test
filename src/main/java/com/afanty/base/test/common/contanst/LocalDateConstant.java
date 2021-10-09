package com.afanty.base.test.common.contanst;

import java.time.format.DateTimeFormatter;

/**
 * <p>
 * LocalDate日期格式常量类
 * </p>
 *
 * @author yejx
 * @date 2021/5/5
 */
public class LocalDateConstant {

    public static final DateTimeFormatter FORMATTER_DATE_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter FORMATTER_DATE_PATTERN_1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static final DateTimeFormatter FORMATTER_DATE_PATTERN_2 = DateTimeFormatter.ofPattern("yyyy/M/dd");

    public static final DateTimeFormatter FORMATTER_DATE_PATTERN_3 = DateTimeFormatter.ofPattern("yyyy/MM/d");

    public static final DateTimeFormatter FORMATTER_DATE_PATTERN_4 = DateTimeFormatter.ofPattern("yyyy/M/d");

    public static final DateTimeFormatter FORMATTER_DATE_YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter FORMATTER_DATE_HH_MM = DateTimeFormatter.ofPattern("HH:mm");

    public static final DateTimeFormatter FORMATTER_DATE_HH_MM_SS = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter FORMATTER_DATE_HHMM = DateTimeFormatter.ofPattern("HHmm");

    public static final DateTimeFormatter FORMATTER_DATE_HHMMSS = DateTimeFormatter.ofPattern("HHmmss");

    public static final DateTimeFormatter FORMATTER_DATE_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter FORMATTER_DATE_YYYY_MM_DD_HHMM = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final DateTimeFormatter FORMATTER_DATE_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter FORMATTER_DATE_YYYY_MM_DD_HHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");

    public static final DateTimeFormatter FORMATTER_DATE_BEGIN = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");

    public static final DateTimeFormatter FORMATTER_DATE_END = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");

    public static final DateTimeFormatter FORMATTER_DATE_YYYYMMDDHH = DateTimeFormatter.ofPattern("yyyyMMddHH");

    public static final DateTimeFormatter FORMATTER_DATE_YYYYMMDDHHMM = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static final DateTimeFormatter FORMATTER_DATE_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter FORMATTER_DATE_YYYYMMDDHHMMSSSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

}
