package com.afanty.base.test.io.bytestream;

import com.afanty.base.test.utils.AfantyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字节缓冲输出流
 * </p>
 *
 * @author yejx
 * @date 2021/6/8
 */
public class BufferedOutput {

    private static final Logger logger = LoggerFactory.getLogger(BufferedOutput.class);

    public static void main(String[] args) {
        File file = new File("C:\\Users\\yejx\\Desktop\\bufferedOutput.txt");
        FileOutputStream fos;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            List<String> list = data();
            for (String s : list) {
                bos.write(s.getBytes(StandardCharsets.UTF_8)); // 写入8k字节缓冲区
                bos.write(AfantyConstant.NEXT_LINE.getBytes()); // 换行
                bos.flush(); // 刷新到硬盘
            }
            logger.info("执行成功");
        } catch (IOException e) {
            logger.error("文件写入异常：{}", e.toString());
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("缓冲输出流关闭异常：{}", e.getMessage());
                }
            }
        }

    }

    public static List<String> data() {
        List<String> list = new ArrayList<>();
        list.add("武汉阿凡提科技有限公司");
        list.add("武汉英雄信息技术有限公司");
        return list;
    }
}
