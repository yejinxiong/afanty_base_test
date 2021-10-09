package com.afanty.base.test.thread.base;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <p>
 * 多线程下载网络图片：继承Thread类
 * </p>
 *
 * @author yejx
 * @date 2021/5/21
 */
public class MultiThreadExtDownload extends Thread {

    private String remoteUrl;
    private String fileName;

    /**
     * 有参构造器
     *
     * @param remoteUrl
     * @param fileName
     */
    public MultiThreadExtDownload(String remoteUrl, String fileName) {
        this.remoteUrl = remoteUrl;
        this.fileName = fileName;
    }

    /**
     * 分析运行结果：先运行t1，再运行t2，最后运行t3
     * 实际运行结果：无序
     *
     * @param args
     */
    public static void main(String[] args) {
        String remoteUrl1 = "https://img-sz.topys.cn/2021-05-18/1621303367848.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";
        String remoteUrl2 = "https://img-sz.topys.cn/2021-05-16/1621170600255.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";
        String remoteUrl3 = "https://img-sz.topys.cn/2021-05-19/1621414838101.png?x-oss-process=image/resize,m_fill,h_1000,w_1880,limit_0/format,webp";

        String fileName1 = "C:\\Users\\yejx\\Desktop\\pic1.png";
        String fileName2 = "C:\\Users\\yejx\\Desktop\\pic2.png";
        String fileName3 = "C:\\Users\\yejx\\Desktop\\pic3.png";

        MultiThreadExtDownload t1 = new MultiThreadExtDownload(remoteUrl1, fileName1);
        MultiThreadExtDownload t2 = new MultiThreadExtDownload(remoteUrl2, fileName2);
        MultiThreadExtDownload t3 = new MultiThreadExtDownload(remoteUrl3, fileName3);

        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 下载图片的线程的执行体
     */
    @Override
    public void run() {
        WebDownload webDownload = new WebDownload();
        webDownload.download(remoteUrl, fileName);
        System.out.println("文件下载成功：" + fileName);
    }
}

/**
 * 下载器
 */
class WebDownload {
    /**
     * 下载方法
     *
     * @param remoteUrl 远程图片地址
     * @param fileName  文件存放路径
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
