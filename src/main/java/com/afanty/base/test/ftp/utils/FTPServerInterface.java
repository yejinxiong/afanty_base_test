package com.afanty.base.test.ftp.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FTPServerInterface {

    /**
     * 文件下载，用完即关
     *
     * @param ftpFile
     * @param saveLocalFile
     * @return
     * @throws Exception
     */
    public boolean fastDownload(String ftpFile, String saveLocalFile) throws Exception;

    /**
     * 下载文件（需手动关闭）
     *
     * @param ftpFile
     * @param saveLocalFile
     * @return
     * @throws Exception
     */
    public boolean download(String ftpFile, String saveLocalFile) throws Exception;

    /**
     * 上传文件
     *
     * @param file
     * @param dst
     * @return
     * @throws Exception
     */
    public boolean upload(File file, String dst) throws Exception;

    /**
     * 判断目录是否存在
     *
     * @param path 路径
     * @return
     * @throws Exception
     */
    public boolean isExistsDir(String path) throws Exception;

    /**
     * 关闭连接服务
     *
     * @throws IOException
     */
    public void close() throws IOException;

    /**
     * 获取指定路径下的所有文件名称
     *
     * @param path 路径
     * @return 列表
     */
    public List<String> listFiles(String path) throws Exception;

}
