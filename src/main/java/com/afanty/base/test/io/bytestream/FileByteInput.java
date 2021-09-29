package com.afanty.base.test.io.bytestream;

import com.afanty.base.test.utils.AfantyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 * 文件字节输入流：读取文件内容
 * <p>
 * 文件内容：
 * 20210530|2|信阳联通“智慧助老”开启美好智慧生活！|7|https://c.m.163.com/news/a/GB705S2805387RYL.html
 * 20210530|14|运营商布局“39不限量”套餐！补全4G套餐，网友：等你好久了|15|https://3g.k.sohu.com/t/n535476771
 * 20210530|4|联通电话号码查询联通电话号码网上选|1|https://www.hcside.com/news/1135296.html
 * </p>
 *
 * @author yejx
 * @date 2021/5/31
 */
public class FileByteInput {

    private static final Logger logger = LoggerFactory.getLogger(FileByteInput.class);

    public static void main(String[] args) {
        // 方案二：使用common-io的FileUtils工具类
//        try {
//            File file = new File("C:\\Users\\yejx\\Desktop\\yqrd.txt");
//            List lines = FileUtils.readLines(file, AfantyConstant.ENCODE_GBK);
//            if (lines.size() > 0) {
//                for (Object line : lines) {
//                    System.out.println(line.toString());
//                }
//                logger.info("方案二执行成功");
//            } else {
//                logger.error("方案二执行失败，该文件内容为空");
//            }
//        } catch (IOException e) {
//            logger.error("方案二执行失败，文件读取异常：{}", e.getMessage());
//        }


        // 方案一：原生
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\yejx\\Desktop\\yqrd.txt");
//            // 1. 一个字节一个字节读取
//            int data;
//            while ((data = fis.read()) != -1) {
//                System.out.print((char) data);
//            }

//            // 2. 从字节缓冲区读取：一次读取多个字节
//            byte[] bytes = new byte[1024];// 每次缓冲的字节数
//            int data;
//            while ((data = fis.read(bytes)) != -1) {
//             // 注意：String不要导错包
//                System.out.println(new String(bytes, 0, data));
//            }

            // 3. 从字节缓冲区读取：一次读取一行
            InputStreamReader reader = new InputStreamReader(fis, AfantyConstant.ENCODE_GBK);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            logger.info("方案一执行成功");
        } catch (Exception e) {
            logger.error("方案一执行失败，文件读取异常：{}", e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("方案一执行失败，输入流关闭异常：{}", e.getMessage());
                }
            }
        }

    }
}
