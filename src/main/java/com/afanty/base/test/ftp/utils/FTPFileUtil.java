package com.afanty.base.test.ftp.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * FTP下载连接工具类
 *
 * @author jianglong
 */
public class FTPFileUtil implements FTPServerInterface {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private String host;
    private int port;
    private String userName;
    private String password;


    //ftp客户端
    private FTPClient ftp;

    /**
     * 实例化的时候，进行连接
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public FTPFileUtil(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        try {
            LOGGER.info("创建FTP连接中，host[" + host + "]" +
                    ",port[" + port + "]," +
                    "userName[" + userName + "]," +
                    "password[" + password + "]");
            connectFtp();
        } catch (Exception e) {
            ftp = null;
            LOGGER.error("创建FTP连接出错，host[" + host + "]" +
                    ",port[" + port + "]," +
                    "userName[" + userName + "]," +
                    "password[" + password + "]", e);
        }
    }

    /**
     * 创建FTP连接
     *
     * @throws Exception
     */
    public void connectFtp() throws Exception {
        ftp = new FTPClient();
        ftp.setDefaultTimeout(5000);
        ftp.setDataTimeout(5000);
        ftp.setConnectTimeout(5000);
        ftp.connect(host, port);
        ftp.login(userName, password);
        LOGGER.info("成功连接FTP " + host);
        ftp.setBufferSize(1024 * 10);
        ftp.setKeepAlive(true);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    /**
     * 文件下载
     *
     * @param ftpFile
     * @param saveLocalFile
     * @return
     * @throws Exception
     */
    @Override
    public boolean download(String ftpFile, String saveLocalFile) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        File file = new File(saveLocalFile);
        if (!file.exists()) {
            File parentFile = new File(file.getParent());
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        ftp.retrieveFile(new String(ftpFile.getBytes("GB2312"), StandardCharsets.ISO_8859_1), fos);
        fos.flush();
        IOUtils.closeQuietly(fos);
        return true;
    }

    @Deprecated
    public InputStream download(String ftpFile) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        ftp.enterLocalPassiveMode(); // 这句话为了解决 长连接下载文件进入假死阻塞状态，坑死老夫啊啊啊啊啊
        InputStream is = ftp.retrieveFileStream(ftpFile);
        if (is != null) {
            ftp.completePendingCommand(); //必须得调用这句话 ，否则下次下载is=null，老夫被坑死了
        }
        return is;
    }

    /**
     * 文件下载，用完即关
     *
     * @param ftpFile
     * @param saveLocalFile
     * @return
     * @throws Exception
     */
    @Override
    public boolean fastDownload(String ftpFile, String saveLocalFile) throws Exception {
        boolean b = download(ftpFile, saveLocalFile);
        try {
            close();
        } catch (Exception e) {
        }
        return b;
    }

    /**
     * 关闭Ftp
     */
    @Override
    public void close() throws IOException {
        ftp.logout();
        ftp.disconnect();
        ftp = null;
    }

    /**
     * 获取指定路径下的目录名称
     *
     * @param path 路径
     * @return 列表
     */

    public List<String> listDirectories(String path) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        FTPFile[] ftpFiles = ftp.listDirectories(path);
        List<String> list = new ArrayList<String>();
        for (FTPFile ftpFile : ftpFiles) {
            list.add(ftpFile.getName());
        }
        return list;
    }

    /**
     * 进入指定目录
     *
     * @param parentPath
     * @return
     * @throws Exception
     */
    public boolean cd(String parentPath) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        return ftp.changeWorkingDirectory(parentPath);
    }

    /**
     * 上传文件
     *
     * @param file
     * @param dst
     * @throws Exception
     */
    @Override
    public boolean upload(File file, String dst) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        FileInputStream is = null;
        try {
            cd(dst);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            is = new FileInputStream(file);
            ftp.storeFile(file.getName(), is);
        } catch (Exception e) {
            throw new Exception("FTP客户端上传文件出错");
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * 删除Ftp上的文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public boolean delete(String file) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        try {
            return ftp.deleteFile(file);
        } catch (Exception e) {
            throw new Exception("FTP客户端删除文件出错");
        }
    }

    /**
     * 判断目录是否存在
     *
     * @param path 路径
     * @return 是否
     * @throws Exception
     */

    @Override
    public boolean isExistsDir(String path) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        return ftp.changeWorkingDirectory(path);
    }

    /**
     * 下载远程文件（通过本地临时文件流的形式）
     *
     * @param remoteFile 远程文件地址
     * @param tempIs     临时文件流
     * @return 是否
     * @throws Exception
     */

    public boolean downloadByTemp(String remoteFile, InputStream tempIs) throws Exception {
        if (ftp == null) {
            throw new Exception("FTP客户端未连接或创建连接出错");
        }
        return ftp.storeFile(remoteFile, tempIs);
    }

    /**
     * 获取指定路径下的所有文件名称
     *
     * @param path 路径
     * @return 列表
     */
    @Override
    public List<String> listFiles(String path) throws Exception {

        return null;
    }
}
