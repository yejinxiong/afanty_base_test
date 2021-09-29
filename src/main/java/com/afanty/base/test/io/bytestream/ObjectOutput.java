package com.afanty.base.test.io.bytestream;

import com.afanty.base.test.io.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 使用ObjectOutputStream实现对象的序列化
 * <p>
 * 注意：要序列化的类必须实现Serializable接口
 * </p>
 *
 * @author yejx
 * @date 2021/6/13
 */
public class ObjectOutput {

    private static Logger logger = LoggerFactory.getLogger(ObjectOutput.class);

    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        try {
            // 1. 创建对象流
            FileOutputStream fos = new FileOutputStream("C:\\Users\\yejx\\Desktop\\Company.txt");
            oos = new ObjectOutputStream(fos);

//            // 2.1. 序列化（写入操作），单个文件
//            Company company = new Company("1", "91110108681218290D", "北京中云金诺科技有限公司", "孙燕芹");
//            oos.writeObject(company);

            // 2.2. 序列化（写入操作），多个文件
            List<Company> companies = Arrays.asList(
                    new Company("1", "91110108681218290D", "北京中云金诺科技有限公司", "孙燕芹"),
                    new Company("2", "911101087454893573", "北京合力亿捷科技股份有限公司", "曲道俊")
            );
            oos.writeObject(companies);

            logger.info("ObjectOutputStream序列化成功");
        } catch (IOException e) {
            logger.error("ObjectOutputStream序列化异常：{}", e.getMessage());
        } finally {
            // 3. 关闭
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.error("ObjectOutputStream关闭异常：{}", e.getMessage());
                }
            }
        }
    }
}
