package com.afanty.base.test.io.bytestream;

import com.afanty.base.test.utils.AfantyConstant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 文件字节输出流
 * </p>
 *
 * @author yejx
 * @date 2021/5/31
 */
public class FileByteOutput {

    private static final Logger logger = LoggerFactory.getLogger(FileByteOutput.class);

    public static void main(String[] args) {
        String[] contentArr = {"20210530|CUCC|2|13405", "20210530|CUCC|0|1559", "20210530|CMCC|2|5155", "20210530|CMCC|0|2137", "20210530|CTCC|2|3201", "20210530|CTCC|0|1018"};

        // 方案二：使用common-io的FileUtils工具类
        try {
            File file = new File("C:\\Users\\yejx\\Desktop\\yqzb2.txt");
            List<String> lines = new ArrayList<>(Arrays.asList(contentArr));
            FileUtils.writeLines(file, AfantyConstant.ENCODE_GBK, lines);
            logger.info("方案二执行成功");
        } catch (IOException e) {
            logger.error("方案二执行失败，文件输出异常：{}", e.getMessage());
        }


        // 方案一：原生
        File file1 = new File("C:\\Users\\yejx\\Desktop\\yqzb1.txt");
        if (file1.exists()) {
            FileOutputStream fos = null;
            try {
                // append为true，表示可以重复向该文件内追加内容
                fos = new FileOutputStream(file1, true);
//                // 1. 直接写入硬盘
//                for (String content : contentArr) {
//                    fos.write(content.getBytes());
//                    fos.write(AfantyConstant.NEXT_LINE.getBytes());
//                }

                // 2. 先写入字节缓冲区，再刷新到硬盘
                OutputStreamWriter osw = new OutputStreamWriter(fos, AfantyConstant.ENCODE_GBK);
                BufferedWriter bw = new BufferedWriter(osw);
                for (String content : contentArr) {
                    bw.write(content);
                    bw.write(AfantyConstant.NEXT_LINE);
                    bw.flush();
                }
                bw.close();
                logger.info("方案一执行成功");
            } catch (Exception e) {
                logger.error("方案一执行失败，文件写入异常：{}", e.getMessage());
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        logger.error("方案一执行失败，输出流关闭异常：{}", e.getMessage());
                    }
                }
            }
        } else {
            logger.error("方案一执行失败，文件不存在");
        }

    }
}
