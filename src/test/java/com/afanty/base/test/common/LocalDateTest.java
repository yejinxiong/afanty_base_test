package com.afanty.base.test.common;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 日期时间测试类：LocalDateTime、LocalDate、LocalTime
 * </p>
 *
 * @author yejx
 * @date 2021/7/27
 */
public class LocalDateTest {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTest.class);

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter dateTimeMsecFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final DateTimeFormatter dateAndStartTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");

    private static final DateTimeFormatter dateAndEndtTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");

    /**
     * 获取当前时间
     */
    @Test
    public void getCurrent() {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println("当前日期时间：" + localDateTime);

            LocalDate localDate = LocalDate.now();
            System.out.println("当前日期：" + localDate);

            LocalTime localTime = LocalTime.now();
            System.out.println("当前时间：" + localTime);

            LocalTime minTime = localTime.with(LocalTime.MIN);
            System.out.println("当天开始时间：" + timeFormatter.format(minTime));

            LocalTime maxTime = localTime.with(LocalTime.MAX);
            System.out.println("当天结束时间：" + timeFormatter.format(maxTime));
        } catch (Exception e) {
            logger.error("获取当前时间转换异常：{}", e.getMessage());
        }
    }

    /**
     * 日期/时间转字符串
     */
    @Test
    public void local2String() {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println("当前日期时间：" + localDateTime);
            System.out.println("当前日期时间字符串1：" + dateFormatter.format(localDateTime));
            System.out.println("当前日期时间字符串2：" + dateTimeFormatter.format(localDateTime));
            System.out.println("当前日期时间字符串3：" + dateAndStartTimeFormatter.format(localDateTime));
            System.out.println("当前日期时间字符串4：" + dateAndEndtTimeFormatter.format(localDateTime));
            System.out.println("当前日期时间字符串5：" + dateTimeMsecFormatter.format(localDateTime));
            System.out.println("当前日期时间字符串5：" + localDateTime.format(dateTimeMsecFormatter) + "\n");

            LocalDate localDate = LocalDate.now();
            System.out.println("当前日期：" + localDate);
            System.out.println("当前日期字符串1：" + dateFormatter.format(localDate));
//            System.out.println("当前日期字符串2：" + dateTimeFormatter.format(localDate));  // 异常
            System.out.println("当前日期字符串3：" + dateAndStartTimeFormatter.format(localDate));
            System.out.println("当前日期字符串4：" + dateAndEndtTimeFormatter.format(localDate));
//            System.out.println("当前日期字符串5：" + dateTimeMsecFormatter.format(localDate));    // 异常
        } catch (Exception e) {
            logger.error("日期/时间转字符串异常：{}", e.getMessage());
        }
    }

    /**
     * 字符串转日期/时间
     */
    @Test
    public void string2Local() {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse("2021-07-29 23:37:25", dateTimeFormatter);
            System.out.println("字符串转日期时间：" + localDateTime);

            LocalDate localDate1 = LocalDate.parse("2021-07-29");
            System.out.println("字符串转日期1：" + localDate1);

            LocalDate localDate2 = LocalDate.parse("20210729", DateTimeFormatter.BASIC_ISO_DATE);
            System.out.println("字符串转日期2：" + localDate2);

            LocalTime localTime = LocalTime.parse("23:37:25");
            System.out.println("字符串转时间：" + localTime);
        } catch (Exception e) {
            logger.error("字符串转日期/时间异常：{}", e.getMessage());
        }
    }

    /**
     * Date转LocalDateTime
     * Date转LocalDate
     * Date转LocalTime
     */
    @Test
    public void date2Local() {
        try {
            Date date = new Date();
            System.out.println("Date：" + date);

            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
            System.out.println("Date转LocalDateTime：" + localDateTime);
            LocalDate localDate = localDateTime.toLocalDate();
            System.out.println("Date转LocalDate：" + localDate);
            LocalTime localTime = localDateTime.toLocalTime();
            System.out.println("Date转LocalTime：" + localTime);
        } catch (Exception e) {
            logger.error("Date转Local异常：{}", e.getMessage());
        }
    }

    /**
     * LocalDateTime转Date
     * LocalDate转Date
     * LocalTime转Date
     */
    @Test
    public void local2Date() {
        try {
            ZoneId zoneId = ZoneId.systemDefault();

            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant1 = localDateTime.atZone(zoneId).toInstant();
            Date localDateTime2Date = Date.from(instant1);
            System.out.println("LocalDateTime转Date：" + localDateTime2Date);

            LocalDate localDate = LocalDate.now();
            Instant instant2 = localDate.atStartOfDay().atZone(zoneId).toInstant();
            Date localDate2Date = Date.from(instant2);
            System.out.println("LocalDate转Date：" + localDate2Date);

            LocalTime localTime = LocalTime.now();
            LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
            Instant instant3 = dateTime.atZone(zoneId).toInstant();
            Date localTime2Date = Date.from(instant3);
            System.out.println("LocalTime转Date：" + localTime2Date);
        } catch (Exception e) {
            logger.error("Date转Local异常：{}", e.getMessage());
        }
    }

    /**
     * 指定日期/时间：具根据体年月日时分秒
     */
    @Test
    public void specify() {
        try {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("当前日期时间：" + dateTimeFormatter.format(now));

            LocalDate localDate = LocalDate.of(2021, 7, 29);
            System.out.println("指定日期：" + dateFormatter.format(localDate));

            LocalTime localTime = LocalTime.of(19, 53, 29);
            System.out.println("指定时间：" + timeFormatter.format(localTime));

            LocalDateTime localDateTime = LocalDateTime.of(2021, 7, 29, 19, 53, 29);
            System.out.println("指定日期和时间：" + dateTimeFormatter.format(localDateTime));
        } catch (Exception e) {
            logger.error("指定日期/时间异常：{}", e.getMessage());
        }
    }

    /**
     * 指定日期/时间：根据变量
     */
    @Test
    public void specifyOffset() {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println("指定当前日期时间：" + dateTimeMsecFormatter.format(localDateTime) + "\n");

            LocalDateTime withSecond = localDateTime.withSecond(5);
            System.out.println("指定当前时间第5秒：" + dateTimeFormatter.format(withSecond));
            LocalDateTime secondOfMinute = localDateTime.with(ChronoField.SECOND_OF_MINUTE, 5);
            System.out.println("指定当前时间第5秒：" + dateTimeFormatter.format(secondOfMinute) + "\n");

            LocalDateTime withMinute = localDateTime.withMinute(2);
            System.out.println("指定当前时间第2分钟：" + dateTimeFormatter.format(withMinute));
            LocalDateTime minuteOfHour = localDateTime.with(ChronoField.MINUTE_OF_HOUR, 2);
            System.out.println("指定当前时间第2分钟：" + dateTimeFormatter.format(minuteOfHour) + "\n");

            LocalDateTime withHour = localDateTime.withHour(3);
            System.out.println("指定当前时间第3小时：" + dateTimeFormatter.format(withHour));
            LocalDateTime hourOfDay = localDateTime.with(ChronoField.HOUR_OF_DAY, 3);
            System.out.println("指定当前时间第3小时：" + dateTimeFormatter.format(hourOfDay));
            LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
            System.out.println("指定今天的凌晨零点：" + dateTimeFormatter.format(startOfDay) + "\n");

            LocalDateTime with = localDateTime.with(ChronoField.DAY_OF_WEEK, 2);
            System.out.println("指定本周第2天：" + dateTimeFormatter.format(with));
            LocalDateTime nextTuesday = localDateTime.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            System.out.println("指定下一个周二：" + dateTimeFormatter.format(nextTuesday));
            LocalDateTime firstTuesdayThisMonth = localDateTime.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
            System.out.println("本月第一个周二：" + dateTimeFormatter.format(firstTuesdayThisMonth));
            LocalDateTime lastTuesdayThisMonth = localDateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.TUESDAY));
            System.out.println("本月最后一个周二：" + dateTimeFormatter.format(lastTuesdayThisMonth));
            LocalDateTime dayOfWeekInMonth = localDateTime.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY));
            System.out.println("本月第2周的周二：" + dateTimeFormatter.format(dayOfWeekInMonth) + "\n");

            LocalDateTime withDayOfMonth = localDateTime.withDayOfMonth(12);
            System.out.println("指定本月第12天：" + dateTimeFormatter.format(withDayOfMonth));
            LocalDateTime dayOfMonth = localDateTime.with(ChronoField.DAY_OF_MONTH, 12);
            System.out.println("指定本月第12天：" + dateTimeFormatter.format(dayOfMonth));
            LocalDateTime firstDayOfMonth = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
            System.out.println("指定本月第1天：" + dateTimeFormatter.format(firstDayOfMonth));
            LocalDateTime lastDayOfMonth = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
            System.out.println("指定本月最后1天：" + dateTimeFormatter.format(lastDayOfMonth));
            LocalDateTime firstDayOfNextMonth = localDateTime.with(TemporalAdjusters.firstDayOfNextMonth());
            System.out.println("指定下个月第1天：" + dateTimeFormatter.format(firstDayOfNextMonth) + "\n");

            LocalDateTime withDayOfYear = localDateTime.withDayOfYear(200);
            System.out.println("指定本年第200天：" + dateTimeFormatter.format(withDayOfYear));
            LocalDateTime dayOfYear = localDateTime.with(ChronoField.DAY_OF_YEAR, 200);
            System.out.println("指定本年第200天：" + dateTimeFormatter.format(dayOfYear));
            LocalDateTime firstDayOfYear = localDateTime.with(TemporalAdjusters.firstDayOfYear());
            System.out.println("指定本年第1天：" + dateTimeFormatter.format(firstDayOfYear));
            LocalDateTime lastDayOfYear = localDateTime.with(TemporalAdjusters.lastDayOfYear());
            System.out.println("指定本年最后1天：" + dateTimeFormatter.format(lastDayOfYear));
            LocalDateTime firstDayOfNextYear = localDateTime.with(TemporalAdjusters.firstDayOfNextYear());
            System.out.println("指定明年第1天：" + dateTimeFormatter.format(firstDayOfNextYear) + "\n");

            LocalDateTime withMonth = localDateTime.withMonth(6);
            System.out.println("指定本年第6个月：" + dateTimeFormatter.format(withMonth));
            LocalDateTime monthOfYear = localDateTime.with(ChronoField.MONTH_OF_YEAR, 6);
            System.out.println("指定本年第6个月：" + dateTimeFormatter.format(monthOfYear) + "\n");

            LocalDateTime withYear = localDateTime.withYear(1);
            System.out.println("指定第1年：" + dateTimeFormatter.format(withYear) + "\n");

        } catch (Exception e) {
            logger.error("指定日期/时间异常：{}", e.getMessage());
        }
    }

    /**
     * 日期/时间偏移
     */
    @Test
    public void dateOffset() {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println("当前时间：" + dateTimeFormatter.format(localDateTime));

            LocalDateTime plusSeconds = localDateTime.plusSeconds(5L);
            System.out.println("5秒后：" + dateTimeFormatter.format(plusSeconds));
            LocalDateTime plus1 = localDateTime.plus(5, ChronoUnit.SECONDS);
            System.out.println("5秒后：" + dateTimeFormatter.format(plus1));
            LocalDateTime minusSeconds = localDateTime.minusSeconds(5);
            System.out.println("5秒前：" + dateTimeFormatter.format(minusSeconds));
            LocalDateTime minus1 = localDateTime.minus(5, ChronoUnit.SECONDS);
            System.out.println("5秒前：" + dateTimeFormatter.format(minus1) + "\n");

            LocalDateTime plusMinutes = localDateTime.plusMinutes(2L);
            System.out.println("2分钟后：" + dateTimeFormatter.format(plusMinutes));
            LocalDateTime plus2 = localDateTime.plus(2, ChronoUnit.MINUTES);
            System.out.println("2分钟后：" + dateTimeFormatter.format(plus2));
            LocalDateTime minusMinutes = localDateTime.minusMinutes(2);
            System.out.println("2分钟前：" + dateTimeFormatter.format(minusMinutes));
            LocalDateTime minus2 = localDateTime.minus(2, ChronoUnit.MINUTES);
            System.out.println("2分钟前：" + dateTimeFormatter.format(minus2) + "\n");

            LocalDateTime plusHours = localDateTime.plusHours(3L);
            System.out.println("3小时后：" + dateTimeFormatter.format(plusHours));
            LocalDateTime plus3 = localDateTime.plus(3, ChronoUnit.HOURS);
            System.out.println("3小时后：" + dateTimeFormatter.format(plus3));
            LocalDateTime minusHours = localDateTime.minusHours(3);
            System.out.println("3小时前：" + dateTimeFormatter.format(minusHours));
            LocalDateTime minus3 = localDateTime.minus(3, ChronoUnit.HOURS);
            System.out.println("3小时前：" + dateTimeFormatter.format(minus3) + "\n");

            LocalDateTime plusDays = localDateTime.plusDays(10L);
            System.out.println("10天后：" + dateTimeFormatter.format(plusDays));
            LocalDateTime plus4 = localDateTime.plus(10, ChronoUnit.DAYS);
            System.out.println("10天后：" + dateTimeFormatter.format(plus4));
            LocalDateTime minusDays = localDateTime.minusDays(10);
            System.out.println("10天前：" + dateTimeFormatter.format(minusDays));
            LocalDateTime minus4 = localDateTime.minus(10, ChronoUnit.DAYS);
            System.out.println("10天前：" + dateTimeFormatter.format(minus4));
            LocalDateTime min = localDateTime.minusDays(10).with(LocalTime.MIN);
            System.out.println("10天前00:00:00：" + dateTimeFormatter.format(min));
            LocalDateTime max = localDateTime.minusDays(10).with(LocalTime.MAX);
            System.out.println("10天前23:59:59：" + dateTimeFormatter.format(max) + "\n");

            LocalDateTime plusMonths = localDateTime.plusMonths(2L);
            System.out.println("2个月后：" + dateTimeFormatter.format(plusMonths));
            LocalDateTime plus5 = localDateTime.plus(2, ChronoUnit.MONTHS);
            System.out.println("2个月后：" + dateTimeFormatter.format(plus5));
            LocalDateTime minusMonths = localDateTime.minusMonths(2);
            System.out.println("2个月前：" + dateTimeFormatter.format(minusMonths));
            LocalDateTime minus5 = localDateTime.minus(2, ChronoUnit.MONTHS);
            System.out.println("2个月前：" + dateTimeFormatter.format(minus5) + "\n");

            LocalDateTime plusYears = localDateTime.plusYears(5L);
            System.out.println("5年后：" + dateTimeFormatter.format(plusYears));
            LocalDateTime plus6 = localDateTime.plus(5, ChronoUnit.YEARS);
            System.out.println("5年后：" + dateTimeFormatter.format(plus6));
            LocalDateTime minusYears = localDateTime.minusYears(5);
            System.out.println("5年前：" + dateTimeFormatter.format(minusYears));
            LocalDateTime minus6 = localDateTime.minus(5, ChronoUnit.YEARS);
            System.out.println("5年前：" + dateTimeFormatter.format(minus6) + "\n");

            LocalDateTime plusWeeks = localDateTime.plusWeeks(1L);
            System.out.println("1周后：" + dateTimeFormatter.format(plusWeeks));
            LocalDateTime plus7 = localDateTime.plus(1, ChronoUnit.WEEKS);
            System.out.println("1周后：" + dateTimeFormatter.format(plus7));
            LocalDateTime minusWeeks = localDateTime.minusWeeks(1);
            System.out.println("1周前：" + dateTimeFormatter.format(minusWeeks));
            LocalDateTime minus7 = localDateTime.minus(1, ChronoUnit.WEEKS);
            System.out.println("1周前：" + dateTimeFormatter.format(minus7) + "\n");
        } catch (Exception e) {
            logger.error("日期/时间偏移异常：{}", e.getMessage());
        }
    }

    /**
     * 获取两日期之间相隔时间/天数
     * 注意：计算结果为截至昨天
     * <p>
     * Duration：用于计算LocalDateTime差值（差一秒，就不算1分钟，不算1小时，不算1天）
     * Period：用于计算LocalDate差值
     */
    @Test
    public void getBetweenDays() {
        try {
            // 1. 获取时间
            LocalDateTime hireDateTime = LocalDateTime.of(2020, 12, 1, 8, 30, 0);
            System.out.println("入职日期时间：" + hireDateTime);
            LocalDateTime currentDateTime = LocalDateTime.of(2021, 1, 2, 8, 29, 59);
            LocalDateTime yesterdayLastTime = currentDateTime.minusDays(1);
            System.out.println("昨天日期时间：" + currentDateTime);
            System.out.println("当前日期时间：" + yesterdayLastTime);
            // 2. 计算时间差
            Duration duration = Duration.between(hireDateTime, currentDateTime);
            long durationDays = duration.toDays();
            long durationHours = duration.toHours();
            long durationMinutes = duration.toMinutes();
            long durationSeconds = duration.getSeconds();
            long durationMillis = duration.toMillis();
            System.out.println("合计" + durationDays + "天数：");
            System.out.println("合计" + durationHours + "小时：");
            System.out.println("合计" + durationMinutes + "分钟：");
            System.out.println("合计" + durationSeconds + "秒：");
            System.out.println("合计" + durationMillis + "毫秒：" + "\n");

            // 1. 获取时间
            LocalDate hireDate = hireDateTime.toLocalDate();
            System.out.println("入职日期：" + hireDate);
            LocalDate currentDate = currentDateTime.toLocalDate();
            LocalDate yesterdayDate = currentDate.minusDays(1);
            System.out.println("昨天日期：" + yesterdayDate);
            System.out.println("当前日期：" + currentDate);
            // 2. 计算时间差：几年几个月几天
            Period period = Period.between(hireDate, currentDate);
//                Period period = hireDate.until(currentDate);
            int periodYears = period.getYears();
            int periodMonths = period.getMonths();
            int periodDays = period.getDays();
            System.out.println("相隔时间：" + periodYears + "年" + periodMonths + "月" + periodDays + "天");
            // 3. 计算天数
            long hireDays = currentDate.toEpochDay() - hireDate.toEpochDay();
            System.out.println("合计天数：" + hireDays);
        } catch (Exception e) {
            logger.error("计算两日期之间相隔时间/天数异常：{}", e.getMessage());
        }
    }

    /**
     * 是否闰年
     */
    @Test
    public void leapYear() {
        try {
            LocalDate localDate = LocalDate.now();
            if (localDate.isLeapYear()) {
                System.out.println(localDate.getYear() + "年是闰年");
            } else {
                System.out.println(localDate.getYear() + "年不是闰年");
            }
        } catch (Exception e) {
            logger.error("是否闰年异常：{}", e.getMessage());
        }
    }

    /**
     * 获取月份最大天数
     */
    @Test
    public void lengthOfMonth() {
        try {
            YearMonth yearMonth = YearMonth.now();
            System.out.println("本月最大天数：" + yearMonth.lengthOfMonth());
        } catch (Exception e) {
            logger.error("获取月份最大天数异常：{}", e.getMessage());
        }
    }

    /**
     * 生日提醒
     */
    @Test
    public void birthdayReminder() {
        try {
            LocalDate localDate1 = LocalDate.now();
            LocalDate localDate2 = LocalDate.of(1996, 2, 4);

            MonthDay birthday = MonthDay.of(localDate2.getMonth(), localDate2.getDayOfMonth());
            System.out.println("生日：" + birthday);

            MonthDay today = MonthDay.from(localDate1);
            System.out.println("今天：" + today);

            if (today.equals(birthday)) {
                System.out.println("生日快乐");
            } else {
                System.out.println("你的生日还没到");
            }
        } catch (Exception e) {
            logger.error("生日提醒异常：{}", e.getMessage());
        }
    }

    /**
     * 获取连续日期：从昨天开始计算
     */
    @Test
    public void runningDays() {
        List<String> list = new ArrayList<>();
        String[] arr = {"2021-05-31", "2021-06-01", "2021-06-03", "2021-06-04", "2021-06-05", "2021-06-06", "2021-06-07", "2021-06-08"};
        List<String> list1 = Arrays.asList(arr);
        logger.info("排序之前：{}", list1);
        List<String> dateList = Arrays.stream(arr).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        logger.info("排序之后：{}", dateList);
        LocalDate today = LocalDate.parse("2021-06-10");
        logger.info("今天日期：{}", dateFormatter.format(today));
        LocalDate yesterday = today.minusDays(1);
        logger.info("昨天日期：{}", dateFormatter.format(yesterday));
        try {
            // 如果日期集合的第一个日期是昨天，才会往下判断是否为连续日期
            if (dateFormatter.format(yesterday).equals(dateList.get(0))) {
                list.add(dateList.get(0));
                // 通过比较dateList[i]的前一天日期是否为dateList[i+1]，来判断是否为连续日期
                for (int i = 0; i < dateList.size() - 1; i++) {
                    LocalDate preDay = LocalDate.parse(dateList.get(i)).minusDays(1);
                    String preDayStr = dateFormatter.format(preDay);
                    if (preDayStr.equals(dateList.get(i + 1))) {
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
    public void getSpanMonthList() {
        String startDateTimeStr = "2021-04-01 13:23:51";
        String endDateTimeStr = "2021-06-03 20:32:13";
        List<Map<String, String>> list = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, dateTimeFormatter);
        try {
            // 获取结束时间 与 开始时间 跨越的年数
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            Period period = Period.between(startDate, endDate);
            int years = period.getYears();
            // 获取结束时间 与 开始时间 跨越的月数
            int months = period.getMonths() + 1 + years * 12;
            for (int i = 0; i < months; i++) {
                Map<String, String> map = new HashMap<>();
                // 在开始时间月份的基础上增加，依次往后推算
                LocalDateTime currentMonth = startDateTime.plusMonths(i);
                if (i == 0) {
                    map.put("startTime", dateTimeFormatter.format(startDateTime));
                } else {
                    map.put("startTime", dateAndStartTimeFormatter.format(currentMonth));
                }
                if (i == months - 1) {
                    map.put("endTime", dateTimeFormatter.format(endDateTime));
                } else {
                    // 获取当月最后一天
                    LocalDateTime lastDay = currentMonth.with(TemporalAdjusters.lastDayOfMonth());
                    map.put("endTime", dateAndEndtTimeFormatter.format(lastDay));
                }
                list.add(map);
            }
            logger.info("跨越的月份：{}", list);
        } catch (Exception e) {
            logger.error("根据开始/结束时间，计算跨越的月份：{}", e.getMessage());
        }
    }


}
