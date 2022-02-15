package com.afanty.base.test.common;

import com.afanty.base.test.common.contanst.EncodeConstant;
import com.afanty.base.test.lambda.entity.Employee;
import com.afanty.base.test.lambda.entity.RemedySheet;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
     * <p>
     * 注意：Collectors.groupingBy()默认是用 HashMap 存放分组后的结果，但是由于HashMap是无序的，所以最终分组后的结果顺序与原数据的顺序不一致，所以应该使用 LinkedHashMap 保存分组的数据
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
        System.out.println("原数据：" + JSONObject.toJSONString(listUser));

        Map<String, List<Employee>> groupDisorder = listUser.stream().collect(Collectors.groupingBy(Employee::getName));
        System.out.println("无序：" + JSONObject.toJSONString(groupDisorder));

        Map<String, List<Employee>> groupOrder = listUser.stream().collect(Collectors.groupingBy(Employee::getName, LinkedHashMap::new, Collectors.toList()));
        System.out.println("有序：" + JSONObject.toJSONString(groupOrder));
    }

    /**
     * 使用Stream流 根据实体属性 去重
     */
    @Test
    public void testDistinct() {
        List<Employee> listUser = new ArrayList<Employee>() {
            {
                this.add(new Employee("张三", 23, 5000.00));
                this.add(new Employee("张三", 18, 4000.00));
                this.add(new Employee("张三", 23, 6000.00));
                this.add(new Employee("李四", 25, 7000.00));
            }
        };
        // 1. 根据多个属性去重“姓名-年龄”
        List<Employee> newList1 = listUser.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(e -> e.getName() + "-" + e.getAge()))), ArrayList::new));
        System.out.println("多字段去重：" + newList1.toString());

        // 2. 根据单个属性去重“姓名”
        List<Employee> newList2 = listUser.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::getName))), ArrayList::new));
        System.out.println("单字段去重：" + newList2.toString());

        // 3. 获取某属性值，并对属性值去重
        List<String> nameList = listUser.stream().map(Employee::getName).distinct().collect(Collectors.toList());
        System.out.println("获取name并去重：" + nameList.toString());

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
     * 根据时间（Date）筛选list数据
     */
    @Test
    public void filterByDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> list = new ArrayList<>();
        Calendar c1 = Calendar.getInstance();
        c1.set(2021, Calendar.JULY, 1, 18, 20, 0);
        Date time1 = c1.getTime();
        list.add(time1);

        Calendar c2 = Calendar.getInstance();
        c2.set(2021, Calendar.JUNE, 30, 18, 20, 0);
        Date time2 = c2.getTime();
        list.add(time2);

        Calendar c3 = Calendar.getInstance();
        c3.set(2021, Calendar.JUNE, 29, 18, 20, 0);
        Date time3 = c3.getTime();
        list.add(time3);

        Calendar c4 = Calendar.getInstance();
        c4.set(2021, Calendar.JUNE, 28, 18, 20, 0);
        Date time4 = c4.getTime();
        list.add(time4);

        Calendar c5 = Calendar.getInstance();
        c5.set(2021, Calendar.JUNE, 27, 18, 20, 0);
        Date time5 = c5.getTime();
        list.add(time5);

        list.forEach(d -> System.out.println(sdf.format(d)));
        System.out.println("---------------------------------------------");

        Calendar c6 = Calendar.getInstance();
        c6.set(2021, Calendar.JULY, 1, 23, 59, 59);
        Date yesterday = c6.getTime();

        c6.add(Calendar.DATE, -3);
        c6.set(c6.get(Calendar.YEAR), c6.get(Calendar.MONTH), c6.get(Calendar.DATE), 0, 0, 0);
        Date ago4 = c6.getTime();

        // 时间条件筛选
        List<Date> agoList4 = list.stream().filter(d -> d.after(ago4) && d.before(yesterday)).collect(Collectors.toList());
        agoList4.forEach(d -> System.out.println(sdf.format(d)));

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

    /**
     * url解码&编码
     */
    @Test
    public void testDecoderAndEncoder() {
        try {
            String decodeStr = "%25E4%25B8%25AD%25E5%258D%258E%25E4%25BA%25BA%25E5%2590%258D%25E5%2585%25B1%25E5%2592%258C%25E5%259B%25BD";
            String encodeStr = "中户人民共和国";
            String decode = URLDecoder.decode(decodeStr, EncodeConstant.ENCODE_UTF_8);
            String encode = URLEncoder.encode(encodeStr, EncodeConstant.ENCODE_UTF_8);
            System.out.println("解码：" + decode);
            System.out.println("编码：" + encode);
        } catch (UnsupportedEncodingException e) {
            logger.error("转码/解码异常：{}", e.getMessage());
        }
    }

}
