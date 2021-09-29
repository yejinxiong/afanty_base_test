package com.afanty.base.test.io.charstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 * 文件字符输入流：读取中文
 * <p>
 * 文件内容：
 * 20210530|2|信阳联通“智慧助老”开启美好智慧生活！|7|https://c.m.163.com/news/a/GB705S2805387RYL.html
 * 20210530|14|运营商布局“39不限量”套餐！补全4G套餐，网友：等你好久了|15|https://3g.k.sohu.com/t/n535476771
 * 20210530|4|联通电话号码查询联通电话号码网上选|1|https://www.hcside.com/news/1135296.html
 * </p>
 *
 * @author yejx
 * @date 2021/6/14
 */
public class FileCharInput {

    private static final Logger logger = LoggerFactory.getLogger(FileCharInput.class);

    public static void main(String[] args) {
        FileReader fr = null;
        try {
            // 1. 创建FileReader文件字符输入流
            fr = new FileReader("C:\\Users\\yejx\\Desktop\\yqrd.txt");
            // 2. 读取
//            // 2.1. 单个字符读取
//            int data;
//            while ((data = fr.read()) != -1) {
//                System.out.print((char)data);
//            }

//            // 2.2. 从字符缓冲流读取：一次读取多个字符
//            char[] chars = new char[1024];// 每次缓冲的字符数
//            int count;
//            while ((count = fr.read(chars)) != -1) {
//                // 注意：String不要导错包
//                System.out.println(new String(chars, 0, count));
//            }

            // 2.3. 从字符缓冲流读取：一次读取一行
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (null != (line = br.readLine())) {
                System.out.println(line);
            }
        } catch (IOException e) {
            logger.error("文件字符流FileReader读取异常：{}", e.getMessage());
        } finally {
            if (null != fr) {
                try {
                    fr.close();
                } catch (IOException e) {
                    logger.error("文件字符流FileReader关闭失败：{}", e.getMessage());
                }
            }
        }
    }
}
