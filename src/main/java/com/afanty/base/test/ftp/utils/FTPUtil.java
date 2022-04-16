package com.afanty.base.test.ftp.utils;

import com.afanty.base.test.common.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.regex.Matcher;

/**
 * FTP工具类
 *
 * @author yejx
 * @date 2022-04-14
 */
public class FTPUtil {

    private final Logger LOGGER = LogManager.getLogger(FTPUtil.class);

    /**
     * ftp地址
     */
    private String host;

    /**
     * ftp端口
     */
    private int port;

    /**
     * ftp账号
     */
    private String account;

    /**
     * ftp密码
     */
    private String password;

    /**
     * ftp客户端
     */
    private FTPClient ftpClient;

    /**
     * 实例化的时候，进行连接
     *
     * @param host     IP
     * @param port     端口
     * @param account 账号
     * @param password 密码
     */
    public FTPUtil(String host, int port, String account, String password) throws Exception {
        this.host = host;
        this.port = port;
        this.account = account;
        this.password = password;
        LOGGER.info("创建FTP连接中...");
        Boolean successFlag = initFtpClient();
        if (successFlag) {
            LOGGER.info("FTP连接成功：host[{}]", host);
        } else {
            throw new CustomException(String.format("FTP连接失败：host[%s], port[%s], userName[%s], password[%s]", host, port, account, password));
        }
    }

    /**
     * 上传文件
     *
     * @param relativePath 文件在ftp根路径下的路径
     * @param fileName     文件名
     * @param inputStream  文件输入流
     * @return
     */
    public boolean uploadFile(String relativePath, String fileName, InputStream inputStream) throws Exception {
        boolean flag;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            flag = createDirecroty(relativePath);
            if (!flag) {
                throw new CustomException("目录创建失败！");
            }
            ftpClient.setControlEncoding("utf-8");
            ftpClient.enterLocalPassiveMode();
            flag = ftpClient.storeFile(fileName, inputStream);
            LOGGER.info("上传成功: {}", relativePath + File.separator + fileName);
        } finally {
            closeFtpClient();
            closeInputStream(inputStream);
        }
        return flag;
    }

    /**
     * 下载文件
     *
     * @param remotePath 文件所在文件夹
     * @param filename   文件名
     * @param localpath  本地存放路径
     * @return
     */
    public boolean downloadFile(String remotePath, String filename, String localpath) {
        boolean flag = false;
        OutputStream outputStream = null;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //切换FTP目录
            ftpClient.changeWorkingDirectory(remotePath);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile file : ftpFiles) {
                if (filename.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + "/" + file.getName());
                    outputStream = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), outputStream);
                    closeOutputStream(outputStream);
                }
            }
            flag = true;
        } catch (Exception e) {
            LOGGER.error("下载文件发生异常：{}", e.getMessage());
        } finally {
            closeFtpClient();
            closeOutputStream(outputStream);
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param remotePath 文件所在文件夹
     * @param filename   文件名
     * @return
     */
    public boolean deleteFile(String remotePath, String filename) {
        boolean flag = false;
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(remotePath);
            ftpClient.dele(filename);
            LOGGER.info("文件删除成功：{}", filename);
            flag = true;
        } catch (Exception e) {
            LOGGER.error("删除文件发生异常：{}", e.getMessage());
        } finally {
            closeFtpClient();
        }
        return flag;
    }

    /**
     * 创建多层目录文件：如果ftp服务器已存在该文件，则不创建，如果不存在，则创建
     *
     * 创建目录或进入目录，不需要完整路径
     *
     * @param relativePath 文件在ftp根路径下的路径
     * @throws Exception
     */
    private boolean createDirecroty(String relativePath) throws Exception {
        boolean flag = true;
        String[] dirs = relativePath.split(Matcher.quoteReplacement(File.separator));
        String charest = ftpClient.getControlEncoding();
        for (String dir : dirs) {
            if (StringUtils.isBlank(dir)) {
                continue;
            }
            String path = new String(dir.getBytes(), charest);
            if (!ftpClient.changeWorkingDirectory(path)) {
                if (ftpClient.makeDirectory(path)) {
                    ftpClient.changeWorkingDirectory(path);
                } else {
                    LOGGER.error("目录创建失败：{}", path);
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 初始化ftp服务器
     */
    private Boolean initFtpClient() throws Exception {
        ftpClient = new FTPClient();
        ftpClient.setDefaultTimeout(3000);
        ftpClient.setDataTimeout(3000);
        ftpClient.setConnectTimeout(3000);
        //连接ftp服务器
        ftpClient.connect(host, port);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //登录ftp服务器
        return ftpClient.login(account, password);
    }

    /**
     * FTP客户端退出并断开连接
     */
    private void closeFtpClient() {
        if (null != ftpClient && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                LOGGER.info("FTP客户端退出并断开连接！");
            } catch (IOException e) {
                LOGGER.error("FTP客户端退出并断开连接异常：{}", e.getMessage());
            }
        }
    }

    /**
     * 关闭输出流
     */
    private void closeOutputStream(OutputStream outputStream) {
        if (null != outputStream) {
            try {
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error("输出流OutputStram关闭异常：{}", e.getMessage());
            }
        }
    }

    /**
     * 关闭输入流
     */
    private void closeInputStream(InputStream inputStream) {
        if (null != inputStream) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error("输出流InputStream关闭异常：{}", e.getMessage());
            }
        }
    }
}