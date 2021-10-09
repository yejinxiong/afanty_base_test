package com.afanty.base.test.ftp.utils;

/**
 * @author yejx
 * @date 2020/4/20
 */
public class FtpConfigurBean {
    private int index;
    private String parentPath;
    private String host;
    private int port;
    private String userName;
    private String password;
    private String type;

    public FtpConfigurBean() {
    }

    public FtpConfigurBean(String info) throws Exception {
        if (info != null && !"".equals(info.trim())) {
            String[] infos = info.split("\\|");
            if (infos.length != 6) {
                throw new Exception("ftp信息必须为6个，info:" + info);
            } else {
                this.host = infos[0];
                this.port = Integer.valueOf(infos[1]);
                this.userName = infos[2];
                this.password = infos[3];
                this.parentPath = infos[4];
                this.type = infos[5];
            }
        } else {
            throw new Exception("ftp信息不能为空");
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
