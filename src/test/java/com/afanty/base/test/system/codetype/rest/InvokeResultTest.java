package com.afanty.base.test.system.codetype.rest;

import com.afanty.base.test.system.codetype.rest.entity.InvokeResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

/**
 * @Author yejx
 * @Date 2022/1/28
 */
public class InvokeResultTest {

    @Test
    public void test() {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.setSuccess(true);

        // 使用fastjson(1.2.58)序列化InvokeResult成字符串并输出
        System.out.println("Serialized by fastjson：" + JSONObject.toJSONString(invokeResult) + "\n");

        // 使用Gson(2.8.9)序列化InvokeResult成字符串并输出
        Gson gson = new Gson();
        System.out.println("Serialized by Gson：" + gson.toJson(invokeResult) + "\n");

        /*
        分析：
            fastjson是通过反射，遍历出该类中的所有getter方法，得到getSuccess和getMsg，
            然后根据JavaBeans规则，Java会认为这是两个属性msg和success的值，得到序列化结果：{"msg":"调用成功","success":true}

            gson是通过反射，遍历该类中的所有属性，并把其值序列化成json：{"isSuccess":true}

            由于不同的序列化工具，在进行序列化的时候使用到的策略是不一样的，所以，对于同一个类的同一个对象的序列化结果可能是不同的。

        结论：
            建议使用success而不是 isSuccess 这种形式。 这样，该类里面的成员变量是success，getter方法是isSuccess/getSuccess，这是完全符合JavaBeans规范的。无论哪种序列化框架，执行结果都一样。就从源头避免了这个问题。

        备注：
            getter方法的命名，直接在属性前面加上get这种方式，也不会出现上述情况
         */
    }

}
