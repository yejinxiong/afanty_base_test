package com.afanty.base.test.io.bytestream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 * 文件字节流拷贝文件
 * </p>
 *
 * @author yejx
 * @date 2021/6/1
 */
public class FileCopy {

    private static final Logger logger = LoggerFactory.getLogger(FileByteOutput.class);

    public static void main(String[] args) {
        // 方案二：使用common-io的FileUtils工具类
        try {
            File source = new File("C:\\Users\\yejx\\Desktop\\afanty.jpg");
            File target = new File("C:\\Users\\yejx\\Desktop\\afanty02.jpg");
            FileUtils.copyFile(source, target);
            logger.info("方案二执行成功");
        } catch (IOException e) {
            logger.error("方案二执行失败，文件拷贝异常：{}", e.getMessage());
        }


        // 方案一：原生
        File sourceFile = new File("C:\\Users\\yejx\\Desktop\\afanty.jpg");
        File targetFile = new File("C:\\Users\\yejx\\Desktop\\afanty01.jpg");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        if (sourceFile.exists()) {
            try {
                fis = new FileInputStream(sourceFile);
                fos = new FileOutputStream(targetFile);
                byte[] buffer = new byte[4096];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                logger.info("方案一执行成功");
            } catch (IOException e) {
                logger.error("方案一执行失败，文件输入/输出异常：{}", e.getMessage());
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        logger.error("方案一执行失败，输出流关闭异常：{}", e.getMessage());
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        logger.error("方案一执行失败，输入流关闭异常：{}", e.getMessage());
                    }
                }
            }
        } else {
            logger.error("方案一执行失败，文件不存在");
        }

    }
}
