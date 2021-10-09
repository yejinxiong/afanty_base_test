package com.afanty.base.test.common;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 日期时间测试类：Calender
 * </p>
 *
 * @author yejx
 * @date 2021/7/29
 */
public class CalenderTest {

    private static final Logger logger = LoggerFactory.getLogger(CalenderTest.class);

    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat sdfDateTimeMsec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

    private static final SimpleDateFormat sdfDateAndStartTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    private static final SimpleDateFormat sdfDateAndEndtTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

    /**
     * 获取当前时间
     */
    @Test
    private void getCurrent() {
        Calendar calendar = Calendar.getInstance();
        try {
            System.out.println("当前日期时间：" + sdfDateTime.format(calendar.getTime()));
        } catch (Exception e) {
            logger.error("获取当前时间异常：{}", e.getMessage());
        }
    }

    /**
     * 日期/时间转字符串
     */
    @Test
    private void date2String() {
        Calendar calendar = Calendar.getInstance();
        try {
            System.out.println("当前时间对象：" + calendar.getTime());
            System.out.println("当前日期：" + sdfDate.format(calendar.getTime()));
            System.out.println("当前时间：" + sdfTime.format(calendar.getTime()));
            System.out.println("当前日期时间：" + sdfDateTime.format(calendar.getTime()));
            System.out.println("当天开始时间：" + sdfDateAndStartTime.format(calendar.getTime()));
            System.out.println("当天结束时间：" + sdfDateAndEndtTime.format(calendar.getTime()));
            System.out.println("当前日期时间：" + sdfDateTimeMsec.format(calendar.getTime()));
        } catch (Exception e) {
            logger.error("日期/时间转字符串异常：{}", e.getMessage());
        }
    }

    /**
     * 字符串转日期/时间
     */
    @Test
    private void string2Date() {
        try {
            Date time = sdfTime.parse("23:37:25");
            System.out.println("时间转Date：" + time);

            Date date = sdfDate.parse("2021-07-29");
            System.out.println("日期转Date：" + date);

            Date dateTime = sdfDateTime.parse("2021-07-29 23:37:25");
            System.out.println("日期时间转Date：" + dateTime);

        } catch (Exception e) {
            logger.error("字符串转日期/时间异常：{}", e.getMessage());
        }
    }

    /**
     * Date转Calendar
     */
    @Test
    private void date2Calendar() {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdfDateTime.parse("2021-07-29 23:37:25");
            calendar.setTime(date);
        } catch (Exception e) {
            logger.error("Date转Calendar异常：{}", e.getMessage());
        }
    }

    /**
     * Calendar转Date
     */
    @Test
    private void calendar2Date() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.getTime();
        } catch (Exception e) {
            logger.error("Calendar转Date异常：{}", e.getMessage());
        }
    }

    /**
     * 指定日期/时间：具根据体年月日时分秒
     */
    @Test
    private void specify() {
        try {
            Calendar calendar = Calendar.getInstance();
            System.out.println("当前日期时间：" + sdfDateTime.format(calendar.getTime()));

            calendar.set(2021, Calendar.JULY, 28);
            System.out.println("指定日期：" + sdfDate.format(calendar.getTime()));

            calendar.set(Calendar.HOUR, 19);
            calendar.set(Calendar.MINUTE, 53);
            calendar.set(Calendar.SECOND, 29);
            System.out.println("指定时间：" + sdfTime.format(calendar.getTime()));

            calendar.set(2021, Calendar.JULY, 29, 19, 53, 29);
            System.out.println("指定日期时间：" + sdfDateTime.format(calendar.getTime()));

            calendar.setTime(sdfDateTime.parse("2021-07-29 19:53:29"));
            System.out.println("指定日期时间：" + sdfDateTime.format(calendar.getTime()));
        } catch (Exception e) {
            logger.error("指定日期/时间异常：{}", e.getMessage());
        }
    }

    /**
     * 日期/时间偏移
     */
    @Test
    private void dateOffset() {
        try {
            Calendar c0 = Calendar.getInstance();
            System.out.println("当前时间：" + sdfDateTime.format(c0.getTime()));
            c0.add(Calendar.SECOND, 1);
            System.out.println("1秒后：" + sdfDateTime.format(c0.getTime()));

            Calendar c1 = Calendar.getInstance();
            c1.add(Calendar.MINUTE, 2);
            System.out.println("2分钟后：" + sdfDateTime.format(c1.getTime()));

            Calendar c2 = Calendar.getInstance();
            c2.add(Calendar.HOUR, 3);
            System.out.println("3小时后：" + sdfDateTime.format(c2.getTime()));

            Calendar c3 = Calendar.getInstance();
            c3.add(Calendar.DATE, 10);
            System.out.println("10天后：" + sdfDateTime.format(c3.getTime()));

            Calendar c4 = Calendar.getInstance();
            c4.add(Calendar.MONTH, -2);
            System.out.println("2个月前：" + sdfDateTime.format(c4.getTime()));

            Calendar c5 = Calendar.getInstance();
            c5.add(Calendar.YEAR, -5);
            System.out.println("5年前：" + sdfDateTime.format(c5.getTime()));

            Calendar c6 = Calendar.getInstance();
            c6.add(Calendar.WEEK_OF_MONTH, 1);
            System.out.println("1周后：" + sdfDateTime.format(c6.getTime()));
        } catch (Exception e) {
            logger.error("日期/时间偏移异常：{}", e.getMessage());
        }
    }

    /**
     * 获取两日期之间相隔天数
     */
    @Test
    private void getBetweenDays() {
        // 日期字符串
        String startDateStr = "2021-05-29 17:07:07";
        String endDateStr = "2021-06-02 00:00:00";
        try {
            // 获取日期
            Date start = sdfDateTime.parse(sdfDateAndStartTime.format(sdfDateTime.parse(startDateStr)));
            Date end = sdfDateTime.parse(sdfDateAndEndtTime.format(sdfDateTime.parse(endDateStr)));
            // 获取时间戳
            long s = start.getTime();
            long e = end.getTime();
            // 计算天数
            long one = 1000L * 3600L * 24L;
            long betweenDays = (e - s + one) / one;
            System.out.println("天数：" + betweenDays);
        } catch (ParseException e) {
            logger.error("计算两日期之间天数异常：{}", e.getMessage());
        }
    }

    /**
     * 获取连续日期：从昨天开始计算
     */
    @Test
    private void runningDays() {
        List<String> list = new ArrayList<>();
        String[] arr = {"2021-05-31", "2021-06-01", "2021-06-03", "2021-06-04", "2021-06-05", "2021-06-06", "2021-06-07", "2021-06-08"};
        List<String> list1 = Arrays.asList(arr);
        logger.info("排序之前：{}", list1);
        List<String> dateList = Arrays.stream(arr).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        logger.info("排序之后：{}", dateList);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JUNE, 9);
        logger.info("今天日期：{}", sdfDate.format(calendar.getTime()));
        try {
            // 如果日期集合的第一个日期是昨天，才会往下判断是否为连续日期
            calendar.add(Calendar.DATE, -1);
            String yesterdayStr = sdfDate.format(calendar.getTime());
            logger.info("昨天日期：{}", yesterdayStr);
            if (yesterdayStr.equals(dateList.get(0))) {
                list.add(dateList.get(0));
                // 通过比较dateList[i]的前一天日期是否为dateList[i+1]，来判断是否为连续日期
                for (int i = 0; i < dateList.size() - 1; i++) {
                    calendar.setTime(sdfDate.parse(dateList.get(i)));
                    calendar.add(Calendar.DATE, -1);
                    String preDay = sdfDate.format(calendar.getTime());
                    if (preDay.equals(dateList.get(i + 1))) {
                        list.add(dateList.get(i + 1));
                    } else {
                        break;
                    }
                }
                logger.info("去重之前：{}", list);
                // 去重
                list = list.stream().distinct().collect(Collectors.toList());
                logger.info("去重之后：{}", list);
            } else {
                logger.info("未达到连续天数的条件");
            }
        } catch (Exception e) {
            logger.error("日期转换异常：{}", e.getMessage());
        }
    }

    /**
     * 根据开始/结束时间，计算跨越的月份
     */
    @Test
    private void getSpanMonthList() {
        String startDate = "2021-04-01 13:23:51";
        String endDate = "2021-06-03 20:32:13";
        List<Map<String, String>> list = new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdfDateTime.parse(startDate));
            c2.setTime(sdfDateTime.parse(endDate));
            // 获取结束时间 与 开始时间 相差的年数
            int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            // 获取结束时间 与 开始时间 相差的月数
            int month = c2.get(Calendar.MONTH) + year * 12 - c1.get(Calendar.MONTH);
            for (int i = 0; i <= month; i++) {
                Map<String, String> map = new HashMap<>();
                // 设置开始时间
                c1.setTime(sdfDateTime.parse(startDate));
                // 在开始时间月份的基础上增加，依次往后推算
                c1.add(Calendar.MONTH, i);
                if (i == 0) {
                    map.put("startTime", sdfDateTime.format(c1.getTime()));
                } else {
                    map.put("startTime", sdfDateAndStartTime.format(c1.getTime()));
                }
                if (i == month) {
                    map.put("endTime", endDate);
                } else {
                    // 获取当月最大天数
                    int maxDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
                    // 填充当月最后一天
                    c1.set(Calendar.DAY_OF_MONTH, maxDay);
                    map.put("endTime", sdfDateAndEndtTime.format(c1.getTime()));
                }
                list.add(map);
            }
            logger.info("跨越的月份：{}", list);
        } catch (Exception e) {
            logger.error("根据开始/结束时间，计算跨越的月份：{}", e.getMessage());
        }
    }
}
