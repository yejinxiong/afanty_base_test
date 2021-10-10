package com.afanty.base.test.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * okhttp工具类
 * </p>
 *
 * @author yejx
 * @date 2021/10/10
 */
@Slf4j
@Component
public class OkHttpUtil {

    /**
     * okhttpclient
     */
//    private OkHttpClient okHttpClient = SpringUtilEx.getBean(OkHttpClient.class);

    @Resource
    private OkHttpClient okHttpClient;

    /**
     * 描  述：OKHTTP GET 方式请求
     * 参  数：url 请求的url
     * 参  数：queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * 返回值：java.lang.String
     */
    public String get(String url, Map<String, String> queries, Map<String, String> headers) {
        //构建请求
        Request request = createRequestBuild(buildGetUrl(url, queries), headers).build();

        //请求
        return call(request);
    }

    /**
     * 描  述：OKHTTP POST 方式请求
     * 参  数：url 请求的url
     * 参  数：params form表单POST提交的map
     * 返回值：java.lang.String
     */
    public String post(String url, Map<String, String> params, Map<String, String> headers) {
        //post form形式参数处理
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }

        //构建请求
        Request request = createRequestBuild(url, headers)
                .post(builder.build())
                .build();

        //请求
        return call(request);
    }

    /**
     * 描  述：OKHTTP HEADER 方式请求
     * 参  数：url 请求的url
     * 参  数：queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * 返回值：java.lang.String
     */
    public String getForHeader(String url, Map<String, String> queries, Map<String, String> headers) {
        //构建请求
        Request request = createRequestBuild(buildGetUrl(url, queries), headers).build();

        //请求
        return call(request);
    }

    /**
     * 描  述：OKHTTP POST 方式请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参  数：url 请求的url
     * 参  数：jsonParams 请求的JSON格式字符串
     * 返回值：java.lang.String
     */
    public String postJsonParams(String url, String jsonParams, Map<String, String> headers) {
        //构建请求
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = createRequestBuild(url, headers)
                .post(requestBody)
                .build();

        //请求
        return call(request);
    }

    /**
     * 描  述：OKHTTP POST 方式请求发送xml数据
     * 参  数：url 请求的url
     * 参  数：xml 请求的xmlString
     * 返回值：java.lang.String
     */
    public String postXmlParams(String url, String xml, Map<String, String> headers) {
        //构建请求
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = createRequestBuild(url, headers)
                .post(requestBody)
                .build();

        //请求
        return call(request);
    }

    /**
     * 构建get请求的url
     */
    private String buildGetUrl(String rootUrl, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(rootUrl);
        if (params != null && params.keySet().size() > 0) {
            //url是否已经携带参数
            boolean hasParams = rootUrl.contains("?");
            //在url上添加参数
            for (Object o : params.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                if (hasParams) {
                    //有参数
                    sb.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                } else {
                    //没有参数
                    sb.append("?")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                    hasParams = true;
                }
            }
        }

        return sb.toString();
    }

    /**
     * 创建请求构建器
     */
    private Request.Builder createRequestBuild(String url, Map<String, String> headers) {
        //创建请求构建器
        Request.Builder builder = new Request.Builder();

        //指定url
        if (StringUtils.isNotEmpty(url)) {
            builder.url(url);
        }

        //附加头
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach((key, value) -> {
                if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                    builder.addHeader(key, value);
                }
            });
        }

        return builder;
    }

    /**
     * 请求调用
     */
    private String call(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return null != response.body() ? response.body().string() : "";
            }
        } catch (Exception e) {
            log.error("okhttp3请求调用异常：{}", e.getMessage());
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("response关闭异常：{}", e.getMessage());
                }
            }
        }
        return "";
    }
}
