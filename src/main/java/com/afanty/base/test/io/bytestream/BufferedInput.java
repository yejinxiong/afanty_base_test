package com.afanty.base.test.io.bytestream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * 字节缓冲输入流
 * <p>
 * FileInputStream.read()：直接读取硬盘
 * BufferedInputStream.read()：从缓冲区读取
 * </p>
 *
 * @author yejx
 * @date 2021/6/8
 */
public class BufferedInput {

    private static final Logger logger = LoggerFactory.getLogger(BufferedInput.class);

    public static void main(String[] args) {
        /*
            文件内容为：
            wu han a fan ti xin xi ji shu you xian gong si
            wu han ying xiong xin xi ji shu you xian gong si
         */
        File file = new File("C:\\Users\\yejx\\Desktop\\bufferedInput.txt");
        FileInputStream fis;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            // 1. 使用默认缓冲流BufferedInputStream
            int data;
            while ((data = bis.read()) != -1) {
                System.out.print((char) data);
            }

            // 2. 自己写缓冲流
            byte[] bytes = new byte[1024];
            int count;
            while ((count = fis.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, count));
            }
            logger.info("执行成功");
        } catch (IOException e) {
            logger.error("文件获取异常：{}", e.getMessage());
        } finally {
            // bis会关闭input流，所以不用在此关闭fis
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("缓冲输入流关闭异常：{}", e.getMessage());
                }
            }
        }
    }
}
