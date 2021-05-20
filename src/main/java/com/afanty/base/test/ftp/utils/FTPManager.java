package com.afanty.base.test.ftp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTPManager {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static Map<String, Boolean> useMap = new HashMap<String, Boolean>();

    /**
     * 判断FTP连接是否还有线程使用【暂未使用】
     *
     * @return
     */
    public boolean isUseConn() {
        if (useMap.size() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 线程使用完毕，将当前FTP连接状态置空【暂未使用】
     *
     * @param threadName
     */
    public synchronized static void closeUseConn(String threadName) {
        useMap.remove(threadName);
    }

    /**
     * 启动一个线程时，加入线程名到FTP连接中，表示当前线程使用FTP连接对象【暂未使用】
     *
     * @param threadName
     */
    public synchronized static void addUseConn(String threadName) {
        useMap.put(threadName, true);
    }

    /**
     * 获取Ftp连接信息
     *
     * @return
     */
    public FTPServerInterface getFtpConn(int index, List<FtpConfigurBean> ftpConfigurList) {
        for (FtpConfigurBean ftpConfigurBean : ftpConfigurList) {
            if (index == ftpConfigurBean.getIndex()) {//获取指定索引服务器数据
                return this.getFtpConn(ftpConfigurBean);
            }
        }
        log.error("没有找到FTP：ftp.vocserver" + index + "配置参数无法创建FTP连接，请检查application.properties文件配置是否正确...");
        return null;
    }

    /**
     * 获取Ftp连接信息
     *
     * @return
     */
    public FTPServerInterface getFtpConn(FtpConfigurBean ftpConfigurBean) {
        //区分是FTP还是SFTP下载
        if ("sftp".equals(ftpConfigurBean.getType())) {
            return new SFTPFileUtil(ftpConfigurBean.getHost(),
                    ftpConfigurBean.getPort(),
                    ftpConfigurBean.getUserName(),
                    ftpConfigurBean.getPassword());
        }
        return new FTPFileUtil(ftpConfigurBean.getHost(),
                ftpConfigurBean.getPort(),
                ftpConfigurBean.getUserName(),
                ftpConfigurBean.getPassword());
    }
}
