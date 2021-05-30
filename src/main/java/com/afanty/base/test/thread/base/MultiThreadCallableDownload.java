package com.afanty.base.test.thread.base;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2021/5/25
 */
public class MultiThreadCallableDownload implements Callable<Boolean> {

    private String remoteUrl;
    private String fileName;

    /**
     * 有参构造器
     * @param remoteUrl
     * @param fileName
     */
    public MultiThreadCallableDownload(String remoteUrl, String fileName) {
        this.remoteUrl = remoteUrl;
        this.fileName = fileName;
    }
    @Override
    public Boolean call() throws Exception {
        WebDownload3 webDownload3 = new WebDownload3();
        webDownload3.download(remoteUrl, fileName);
        System.out.println("文件下载成功：" + fileName);
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String remoteUrl1 = "https://img-sz.topys.cn/2021-05-18/1621303367848.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";
        String remoteUrl2 = "https://img-sz.topys.cn/2021-05-16/1621170600255.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";
        String remoteUrl3 = "https://img-sz.topys.cn/2021-05-19/1621414838101.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";

        String fileName1 = "C:\\Users\\yejx\\Desktop\\pic11.png";
        String fileName2 = "C:\\Users\\yejx\\Desktop\\pic12.png";
        String fileName3 = "C:\\Users\\yejx\\Desktop\\pic13.png";

        MultiThreadCallableDownload t1 = new MultiThreadCallableDownload(remoteUrl1, fileName1);
        MultiThreadCallableDownload t2 = new MultiThreadCallableDownload(remoteUrl2, fileName2);
        MultiThreadCallableDownload t3 = new MultiThreadCallableDownload(remoteUrl3, fileName3);

        // 创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> result1 = executorService.submit(t1);
        Future<Boolean> result2 = executorService.submit(t2);
        Future<Boolean> result3 = executorService.submit(t3);
        // 获取结果
        Boolean boolean1 = result1.get();
        Boolean boolean2 = result2.get();
        Boolean boolean3 = result3.get();
        // 关闭服务
        executorService.shutdownNow();

    }
}

/**
 * 下载器
 */
class WebDownload3 {
    /**
     * 下载方法
     * @param remoteUrl 远程图片地址
     * @param fileName 文件存放路径
     */
    public void download(String remoteUrl, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(remoteUrl), new File(fileName));
        } catch (IOException e) {
            String errorMsg = String.format("IO异常，download方法出现问题：%s", e.getMessage());
            System.out.println(errorMsg);
        }
    }
}
