package com.afanty.base.test.io.charstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * 文件字符流拷贝文件
 * <p>
 * 注意：
 * FileReader和FileWriter只能用来复制文本文件，不能复制图片或二进制文件
 * 使用字节流可以复制任意格式的文件
 * </p>
 *
 * @author yejx
 * @date 2021/6/20
 */
public class FileCharCopy {

    private static final Logger logger = LoggerFactory.getLogger(FileCharCopy.class);

    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1. 创建
            fr = new FileReader("C:\\Users\\yejx\\Desktop\\临时文件.txt");
            fw = new FileWriter("C:\\Users\\yejx\\Desktop\\临时文件-copy.txt");
            // 2. 读写
            int data;
            while ((data = fr.read()) != -1) {
                fw.write(data);
                fw.flush();
            }
            logger.error("文件拷贝成功");
        } catch (Exception e) {
            logger.error("文件字符流拷贝文件异常，{}", e.getMessage());
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error("文件字符输出流关闭异常：{}", e.getMessage());
                }
            }
            if (null != fw) {
                try {
                    fr.close();
                } catch (IOException e) {
                    logger.error("文件字符输入流关闭异常：{}", e.getMessage());
                }
            }
        }


    }
}
