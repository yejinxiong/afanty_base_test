package com.afanty.base.test.io.charstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * 文件字符输出流
 * </p>
 *
 * @author yejx
 * @date 2021/6/14
 */
public class FileCharOutput {

    private static final Logger logger = LoggerFactory.getLogger(FileCharOutput.class);

    public static void main(String[] args) {
        String[] contentArr = {"20210530|CUCC|2|13405", "20210530|CUCC|0|1559", "20210530|CMCC|2|5155", "20210530|CMCC|0|2137", "20210530|CTCC|2|3201", "20210530|CTCC|0|1018"};

        FileWriter fw = null;
        try {
            // 1. 创建FileWriter对象
            fw = new FileWriter("C:\\Users\\yejx\\Desktop\\yqrd.txt");
            // 2. 写入
//            // 2.1. 直接通过文件字符输出流写入
//            for (String content : contentArr) {
//                fw.write(content);
//                fw.write(AfantyConstant.NEXT_LINE);
//                fw.flush();
//            }

            // 2.2. 使用BufferWriter字符缓冲流写入
            BufferedWriter bw = new BufferedWriter(fw);
            for (String content : contentArr) {
                bw.write(content);
                bw.newLine();
                bw.flush();
            }

            logger.info("文件写入成功");
            fw.close();
        } catch (IOException e) {
            logger.error("文件字符流FileWriter写入异常：{}", e.getMessage());
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error("文件字符流FileWriter关闭失败：{}", e.getMessage());
                }
            }
        }
    }
}
