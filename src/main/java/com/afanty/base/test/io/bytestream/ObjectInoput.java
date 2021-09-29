package com.afanty.base.test.io.bytestream;

import com.afanty.base.test.io.entity.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * <p>
 * 使用ObjectInputStream实现对象的反序列化（读取并冲构成对象）
 * <p>
 * 注意：要反序列化的类必须实现Serializable接口
 * </p>
 *
 * @author yejx
 * @date 2021/6/13
 */
public class ObjectInoput {

    private static Logger logger = LoggerFactory.getLogger(ObjectInoput.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ObjectInputStream ois = null;
        try {
            // 1. 创建对象流
            FileInputStream fis = new FileInputStream("C:\\Users\\yejx\\Desktop\\Company.txt");
            ois = new ObjectInputStream(fis);

            // 2.1. 读取文件（反序列化），单个对象
//            Company company = (Company)ois.readObject();
//            System.out.println(company);

            // 2.2. 读取文件（反序列化），多个对象，前提是该对象文件是多条数据序列化的
            List<Company> companies = (List<Company>) ois.readObject();
            companies.forEach(System.out::println);

            logger.info("ObjectInputStream反序列化成功");
        } catch (Exception e) {
            logger.error("ObjectInputStream反序列化异常：{}", e.getMessage());
        } finally {
            // 3. 关闭
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("ObjectOutputStream关闭异常：{}", e.getMessage());
                }
            }
        }

    }
}
