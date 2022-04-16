package com.afanty.base.test.ftp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ftp配置
 *
 * @Author yejx
 * @Date 2022/4/14
 */
@Component
@ConfigurationProperties(prefix = "ftp")
public class FTPConfig {

    /**
     * ip地址
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
