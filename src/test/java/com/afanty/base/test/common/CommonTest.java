package com.afanty.base.test.common;

import com.afanty.base.test.lamda.entity.Employee;
import com.afanty.base.test.lamda.entity.RemedySheet;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @Author yejx
 * @Date 2020/5/16
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class CommonTest {

    private static final Logger logger = LoggerFactory.getLogger(CommonTest.class);

    /**
     * 生成32位主键ID-数字
     */
    @Test
    public void generateId() {
        for (int i = 1; i < 35; i++) {
            System.out.println(RandomStringUtils.randomNumeric(32));
        }
    }

    @Test
    public void generateId2() {
        long now = System.currentTimeMillis();
        for (int i = 0; i < 35; i++) {
            System.out.println(now);
            now++;
        }
    }

    /**
     * 生成32位主键ID-数字字母组合
     */
    @Test
    public void generateId3() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replace("-", "");
        System.out.println(id);
    }

    /**
     * 生成指定时间
     */
    @Test
    public void localDatetime() {
        LocalDateTime assginTime = LocalDateTime.of(2021, 5, 5, 23, 49, 57);
        System.out.println(assginTime);
    }

    /**
     * 1、生成18岁到60岁的随机年龄   [18, 60)  不包含60岁
     * new Random().nextInt(42) + 18;
     * <p>
     * 2、生成18岁到60岁的随机年龄 [18, 60]    包含60岁
     * new Random().nextInt(43) + 18;
     * <p>
     * 3、生成-5分到20分的随机成绩 [-5, 20)    不含20分
     * new Random().nextInt(25) - 5;
     * <p>
     * 4、生成-5分到20的的随机成绩 [-5, 20]    包含20分
     * new Random().nextInt(26) - 5;
     */
    @Test
    public void uuidAge() {
        for (int i = 0; i < 10; i++) {
            int age = new Random().nextInt(43) + 18;
            System.out.println(age);
        }
    }

    /**
     * 获取昨天日期
     */
    @Test
    public void test() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
        String yesterdayDay = new SimpleDateFormat("dd").format(calendar.getTime());
        System.out.println("yesterdayDay：" + yesterdayDay);
    }

    /**
     * Double保留两位小数点
     */
    @Test
    public void test01() {
        int a = 1;
        int b = 6;
        double result = new BigDecimal((float) a * 100 / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("计算结果：" + result + "%");
    }

    /**
     * 补救单数据
     *
     * @return
     */
    private List<RemedySheet> remedySheetList() {
        List<RemedySheet> listSheet = new ArrayList<>();
        RemedySheet r1 = new RemedySheet("c945328139ef4c", "8ec0dd9e522e47f783", "BJ10001");
        RemedySheet r2 = new RemedySheet("b42d191a11a6e4", "1850b1305ab547489c", "BJ10002");
        RemedySheet r3 = new RemedySheet("a3ee74afb4e3fe", "356682707c634305a7", "BJ10003");
        RemedySheet r4 = new RemedySheet("31d575872c0f08", "733397b6e2d243048f", "BJ10004");
        RemedySheet r5 = new RemedySheet("47a2e8a79f27b8", "96781b5854fd439a82", "BJ10005");
        RemedySheet r6 = new RemedySheet("e5223489848385", "6ffa5dd499ae4d38a0", "BJ10006");
        RemedySheet r7 = new RemedySheet("3f87d0c8dfbb07", "abfeed2d23b34cf481", "BJ10007");
        RemedySheet r8 = new RemedySheet("034aa340eaf5ab", "82a81aa965464667b2", "BJ10008");
        RemedySheet r9 = new RemedySheet("a1176a29e5a2eb", "ffc035542d5b4dcd82", "BJ10009");
        RemedySheet r10 = new RemedySheet("9d931152a4f672", "f1b56add35444ae4ab", "BJ10010");
        listSheet.add(r1);
        listSheet.add(r2);
        listSheet.add(r3);
        listSheet.add(r4);
        listSheet.add(r5);
        listSheet.add(r6);
        listSheet.add(r7);
        listSheet.add(r8);
        listSheet.add(r9);
        listSheet.add(r10);
        return listSheet;
    }

    /**
     * 接单人数据
     *
     * @return
     */
    private List<Employee> employeeList() {
        List<Employee> listUser = new ArrayList<>();
        Employee e1 = new Employee("张三", 23, 5000.00);
        Employee e2 = new Employee("李四", 18, 4000.00);
        Employee e3 = new Employee("赵武", 25, 7000.00);
        listUser.add(e1);
        listUser.add(e2);
        listUser.add(e3);
        return listUser;
    }

    /**
     * 随机分配
     *
     * @throws InterruptedException
     */
    @Test
    public void testRandom() throws InterruptedException {
        List<RemedySheet> listSheet = this.remedySheetList();
        List<Employee> listUser = this.employeeList();

        int index = listUser.size(); // [0, index)
        System.out.println("原始下标：" + index);
        int ranNumber;
        Random random = new Random();
        for (RemedySheet remedySheet : listSheet) {
            boolean flag;
            Employee employee;
            do {
                ranNumber = random.nextInt(index);
                employee = listUser.get(ranNumber);
                if (employee.getAge() > 24) {
                    System.out.println("分单失败{接单人：" + employee.getName() + "，年龄：" + employee.getAge() + " 超龄：24}");
                    listUser.remove(ranNumber);
                    index = listUser.size();
                    flag = true;
                } else {
                    flag = false;
                }
            } while (flag && index > 0);
            if (index == 0) {
                break;
            }
            remedySheet.setHandleUser(employee.getName());
            listUser.get(ranNumber).setAge(employee.getAge() + 1);
            System.out.println("分单成功{接单人：" + employee.getName() + "，补救流水号：" + remedySheet.getRemedyCode() + "}");
        }
//        Map<String, List<RemedySheet>> collect = listSheet.stream().collect(Collectors.groupingBy(RemedySheet::getHandleUser));
//        System.out.println("分单情况：" + JSONObject.parse(JSON.toJSONString(collect)));
    }

    /**
     * 平均分配
     */
    @Test
    public void testAvg() {
        List<RemedySheet> listSheet = this.remedySheetList();
        List<Employee> listUser = this.employeeList();
        List<Integer> listAge = listUser.stream().map(Employee::getAge).collect(Collectors.toList());

        // 记录原始数据
        List<Employee> tmpListUser = new ArrayList<>(listUser);
        int index = 0;
        for (RemedySheet remedySheet : listSheet) {
            int age;
            boolean flag;
            Employee employee;
            String userName;
            do {
                age = listAge.get(index);
                employee = tmpListUser.get(index);
                userName = employee.getName();
                // 该接单人工单量是否超限
                if (age > 25) { // 已有工单量超过设置的最大工单量、结束本次循环
                    // 原数据同步排除超限的接单人
                    tmpListUser.remove(index);
                    listUser.remove(index);
                    listAge.remove(index);
                    System.out.println(userName + "-超龄-" + age);
                    if (index == tmpListUser.size()) {
                        index = 0;
                    }
                    flag = true;
                } else {
                    flag = false;
                }
            } while (flag && tmpListUser.size() > 0);

            if (listUser.size() <= 0) {
                break;
            } else {
                listAge.set(index, age + 1);//设置数量加1
                if (tmpListUser.size() == 0) { // 人员遍历完了，重新赋值
                    tmpListUser = listUser;
                    index = 0;
                } else if (index == tmpListUser.size() - 1) {
                    index = 0;
                } else {
                    index++;
                }
                System.out.println(userName + "-接单-" + remedySheet.getRemedyCode());
            }
        }
    }

    /**
     * 使用Stream流 根据实体属性 分组
     */
    @Test
    public void testGroupBy() {
        List<Employee> listUser = new ArrayList<>();
        Employee e1 = new Employee("张三", 23, 5000.00);
        Employee e2 = new Employee("张三", 18, 4000.00);
        Employee e3 = new Employee("", 25, 7000.00);
        listUser.add(e1);
        listUser.add(e2);
        listUser.add(e3);
        Map<String, List<Employee>> collect = listUser.stream().collect(Collectors.groupingBy(Employee::getName));
        System.out.println(collect);
    }

    /**
     * 通过不存在的key获取value
     */
    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("01", "2678");
        System.out.println(map.get("02"));

    }

    /**
     * Calendar add()方法的使用
     */
    @Test
    public void testCalendar() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c0 = Calendar.getInstance();
        c0.setTime(sdf.parse("2021-06-03 23:10:00"));
        System.out.println("指定日期：" + sdf.format(c0.getTime()));

        Calendar c1 = Calendar.getInstance();
        System.out.println("当前日期：" + sdf.format(c1.getTime()));

        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DATE, 10);
        System.out.println("10天后：" + sdf.format(c2.getTime()));

        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.MONTH, -2);
        System.out.println("2个月前：" + sdf.format(c3.getTime()));

        Calendar c4 = Calendar.getInstance();
        c4.add(Calendar.YEAR, -5);
        System.out.println("5年前：" + sdf.format(c4.getTime()));

    }

    /**
     * 根据开始/结束时间，计算跨越的月份
     */
    @Test
    public void getMonthList() {
        String startDate = "2021-04-01 13:23:51";
        String endDate = "2021-06-03 20:32:13";
        List<Map<String, String>> list = new ArrayList<>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf1.parse(startDate));
            c2.setTime(sdf1.parse(endDate));
            // 获取结束时间 与 开始时间 相差的年数
            int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            // 获取结束时间 与 开始时间 相差的月数
            int month = c2.get(Calendar.MONTH) + year * 12 - c1.get(Calendar.MONTH);
            for (int i = 0; i <= month; i++) {
                Map<String, String> map = new HashMap<>();
                // 设置开始时间
                c1.setTime(sdf1.parse(startDate));
                // 在开始时间月份的基础上增加，依次往后推算
                c1.add(Calendar.MONTH, i);
                if (i == 0) {
                    map.put("startTime", sdf1.format(c1.getTime()));
                } else {
                    map.put("startTime", sdf2.format(c1.getTime()));
                }
                if (i == month) {
                    map.put("endTime", endDate);
                } else {
                    // 获取当月最大天数
                    int maxDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
                    // 填充当月最后一天
                    c1.set(Calendar.DAY_OF_MONTH, maxDay);
                    map.put("endTime", sdf3.format(c1.getTime()));
                }
                list.add(map);
            }
        } catch (Exception e) {
            logger.error("日期转换异常：{}", e.getMessage());
        }
        System.out.println(list);
    }

    /**
     * 请求流水号：yyyyMMddHHmmssSSS+6位数字随机数
     */
    @Test
    public void transid() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String transid = sdf.format(new Date()) + String.valueOf(1000000 + (int) (Math.random() * 1000000.0D)).substring(1);
        System.out.println(transid);
    }

    /**
     * 解析json字符串：获取什么样的数据会报错
     */
    @Test
    public void parseJson() {
        String respJson = "{\"UNI_BSS_HEAD\":{}}";
        JSONObject responseObject = JSONObject.parseObject(respJson); // 返回：JSONObject
        JSONObject uniBssHead = responseObject.getJSONObject("UNI_BSS_HEAD"); // 返回：JSONObject size=0
        JSONObject data = uniBssHead.getJSONObject("DATA"); // 返回：null
        String values = data.getString("values"); // 异常
        System.out.println(values);
    }

    /**
     * 解析并获取json字符串中的list（该list的泛型没有对应的实体）
     */
    @Test
    public void parseJsonArray() {
        String respJson = "{\"RSP\":{\"DATA\":{\"values\":[{\"codeDesc\":\"待派送\",\"codeValue\":\"1\"},{\"codeDesc\":\"待处理\",\"codeValue\":\"2\"}]}}}";
        JSONObject responseObject = JSONObject.parseObject(respJson); // 返回：JSONObject

        // 非空判断一
//        JSONObject data = responseObject.getJSONObject("RSP").getJSONObject("DATA");
//        if (null != data && data.size() > 0) {
//            List<HashMap> values = JSONObject.parseArray(data.getString("values"), HashMap.class);
//            values.forEach(value -> {
//                JSONObject jsonObject = new JSONObject(value);
//                String codeValue = jsonObject.getString("codeValue");
//                String codeDesc = jsonObject.getString("codeDesc");
//                System.out.println(codeValue + "=>" + codeDesc);
//            });
//        }

        // 非空判断二
        List<HashMap> values = Optional.ofNullable(responseObject)
                .map(response -> response.getJSONObject("RSP"))
                .map(rsp -> rsp.getJSONObject("DATA"))
                .map(data -> JSONObject.parseArray(data.getString("values"), HashMap.class))
                .orElseGet(ArrayList::new);
        values.forEach(map -> {
            String codeValue = MapUtils.getString(map, "codeValue");
            String codeDesc = MapUtils.getString(map, "codeDesc");
            System.out.println(codeValue + "=>" + codeDesc);
        });
    }

}
