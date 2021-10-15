/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:HttpClient.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities;

import com.zpy.yearn.common.YearnException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by zpy on 2018/7/19.
 */
@Component
public class HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    public void setHttpClient(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }
    public HttpEntity post(String url,String body  ) throws YearnException {
        HttpPost request = new HttpPost(url);
        request.setEntity(new StringEntity(body, "UTF-8"));
        request.setHeader("content-type", "application/json");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new YearnException("post failed:"+ExceptionUtils.getStackTrace(e));
        }
        return response.getEntity();

    }

    public HttpEntity getRespContent(String url) {
        return getRespContent(url, null);
    }

    public HttpEntity getRespContent(String url,HashMap<String, String> headers ) {
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            if( headers != null && !headers.isEmpty() ){
                Iterator<String> it = headers.keySet().iterator();
                while( it.hasNext() ) {
                    String key = it.next();
                    httpget.addHeader(key,headers.get(key));
                }
            }
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpClient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
                System.out.println("------------------------------------");
                return entity;
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PreDestroy
    public void destroy(){
        try {
            httpClient.close();
        } catch (IOException e) {
            ExceptionUtils.getStackTrace(e);
        }
    }
}
