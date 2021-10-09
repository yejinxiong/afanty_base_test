package com.afanty.base.test.business.es.config;

import com.afanty.base.test.common.contanst.NetConstant;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * es高级客户端配置
 * </p>
 *
 * @author yejx
 * @date 2021/5/10
 */
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ESHighLevelClientConfig {

    private static final Logger logger = LoggerFactory.getLogger(ESHighLevelClientConfig.class);

    private String host;

    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        logger.info("es高级客户端配置：{}//{}:{}", NetConstant.NET_SCHEME_HTTP, host, port);
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, NetConstant.NET_SCHEME_HTTP)));
        return client;
    }
}
