package com.afanty.base.test.common.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * okhttp3配置类
 * </p>
 *
 * @author yejx
 * @date 2021/10/10
 */
@Slf4j
@Configuration
public class OkHttpConfiguration {

    @Value("${ok.http.connect-timeout}")
    private Integer connectTimeout;

    @Value("${ok.http.read-timeout}")
    private Integer readTimeout;

    @Value("${ok.http.write-timeout}")
    private Integer writeTimeout;

    @Value("${ok.http.max-idle-connections}")
    private Integer maxIdleConnections;

    @Value("${ok.http.keep-alive-duration}")
    private Long keepAliveDuration;

    /**
     * 描  述: 通过@Bean给配置类注入一个OKHttp的客户端对象，加入了ssl的信任代理
     * 参  数:
     * 返回值: okhttp3.OkHttpClient
     */
    @Bean
    public OkHttpClient okHttpClient() {
        //构建 builder 对象
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                //是否开启缓存
                .retryOnConnectionFailure(true)
                // 连接池
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS);
        //构建
        return builder.build();
    }

    /**
     * 描  述:生成ssl工厂类
     * 参  数:
     * 返回值: javax.net.ssl.SSLSocketFactory
     */
    @Bean
    public SSLSocketFactory sslSocketFactory() {
        try {
            //信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("生成ssl工厂类异常：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 描  述: 生成509TrustManager
     * 参  数:
     * 返回值: javax.net.ssl.X509TrustManager
     */
    @Bean
    public X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    /**
     * 描  述: 建立连接池初始化
     * 参  数:
     * 返回值: okhttp3.ConnectionPool
     */
    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES);
    }
}
