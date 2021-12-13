package com.afanty.base.test.ftp.utils;

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * SFTP下载连接工具类
 *
 * @author jianglong
 */
public class SFTPFileUtil implements FTPServerInterface {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private String host;
    private int port;
    private String userName;
    private String password;

    //sftp客户端
    private ChannelSftp sftpClient = null;
    private Session session = null;
    private Channel channel = null;

    /**
     * 实例化的时候，进行连接
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     */
    public SFTPFileUtil(String host, int port, String userName, String password) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        try {
            LOGGER.info("创建SFTP连接中，host[" + host + "]" +
                    ",port[" + port + "]," +
                    "userName[" + userName + "]," +
                    "password[" + password + "]");
            connectFtp();
        } catch (Exception e) {
            sftpClient = null;
            LOGGER.error("创建SFTP连接出错，host[" + host + "]" +
                    ",port[" + port + "]," +
                    "userName[" + userName + "]," +
                    "password[" + password + "]", e);
        }
    }

    /**
     * 创建SFTP连接
     *
     * @throws Exception
     */
    public void connectFtp() throws Exception {
        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
        session.setPassword(password); // 设置密码
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(30 * 1000); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        LOGGER.info("成功连接SFTP " + host);
        LOGGER.info("Opening Channel.");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        sftpClient = (ChannelSftp) channel;
    }

    /**
     * 下载文件（需手动关闭）
     *
     * @param ftpFile
     * @param saveLocalFile
     * @return
     * @throws Exception
     */
    @Override
    public boolean download(String ftpFile, String saveLocalFile) throws Exception {
        if (sftpClient == null) {
            throw new Exception("SFTP客户端未连接或创建连接出错");
        }
        File file = new File(saveLocalFile);
        if (!file.exists()) {
            File parentFile = new File(file.getParent());
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        try {
            sftpClient.get(ftpFile, fos);
        } catch (SftpException se) {
            throw se;
        } finally {
            fos.flush();
            fos.close();
        }
        return true;
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
     * 关闭连接服务
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (sftpClient != null) {
            sftpClient.quit();
        }
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        sftpClient = null;
    }

    /**
     * 获取指定路径下的目录名称
     *
     * @param path 路径
     * @return 列表
     */
    @SuppressWarnings("unchecked")
    public List<String> listDirectories(String path) throws Exception {
        if (sftpClient == null) {
            throw new Exception("SFTP客户端未连接或创建连接出错");
        }
        Vector<String> vts = sftpClient.ls(path);
        List<String> list = new ArrayList<String>();
        for (String ftpFile : vts) {
            list.add(ftpFile);
        }
        return list;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param dst
     * @return
     * @throws Exception
     */
    @Override
    public boolean upload(File file, String dst) throws Exception {
        if (sftpClient == null) {
            throw new Exception("SFTP客户端未连接或创建连接出错");
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            sftpClient.put(is, dst);
        } catch (Exception e) {
            throw new Exception("SFTP客户端上传文件出错");
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * 判断目录是否存在
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    @Override
    public boolean isExistsDir(String path) throws Exception {
        if (sftpClient == null) {
            throw new Exception("SFTP客户端未连接或创建连接出错");
        }
        try {
            sftpClient.ls(path);
        } catch (SftpException se) {
            LOGGER.error(path + "目录不存在...");
            return false;
        }
        return true;
    }

    /**
     * 获取指定路径下的所有文件名称
     *
     * @param path 路径
     * @return 列表
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List<String> listFiles(String path) throws Exception {
        final List<String> list = new ArrayList<>();
        Vector vector = sftpClient.ls(path);
//    	sftpClient.ls(path, new ChannelSftp.LsEntrySelector() {
////            @Override
//            public int select(ChannelSftp.LsEntry entry) {
////            	System.out.println(entry.getFilename() + " --- " + entry.getLongname());
//                if (".".equals(entry.getFilename())
//                        || "..".equals(entry.getFilename())
//                        || entry.getLongname().startsWith("d")) { //d表示为文件夹,则跳过,只下载文件
//                    return CONTINUE;
//                }
//                list.add(entry.getFilename());
//                return CONTINUE;
//            }
//        });

        for (Object obj : vector) {
            if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                LsEntry entry = (com.jcraft.jsch.ChannelSftp.LsEntry) obj;
                if (".".equals(entry.getFilename())
                        || "..".equals(entry.getFilename())
                        || entry.getLongname().startsWith("d")) { //d表示为文件夹,则跳过,只下载文件
                } else {
                    list.add(entry.getFilename());
                }
            }
        }
        return list;
    }
}
