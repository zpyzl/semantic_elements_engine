/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SentenceParser.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.parser;

import com.zpy.yearn.dict.meta.other.SenseFT;
import com.zpy.yearn.dict.meta.predicate.fromTree.VerbFT;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by zhaopengyang on 2018/6/29.
 */
public class SentenceParser {


//TODO check period?
    /*if( sentences.indexOf(EnMarkConstants.PERIOD) != sentences.length() - 1){
            throw new YearnException("period is not at the end of this sentence!");
        }else if( sentences.split(EnMarkConstants.PERIOD).length > 1 ){
            throw new YearnException("more than 1 periods founded in this sentence!");
        }*/

    public static VerbFT parse(String sentenceStr){
        /*Predicate sentence = new Predicate();

        String[] parts = sentenceStr.split(",");
        //firstly determine whether splited by comma are c
        for( String part : parts) {
            parseNoComma(part);
        }
        return sentence;*/
        return null;
    }

    /**
     * Maybe clause or constituents of sentence可能是从句或句子成分
     * @param sentencePart
     */
    public static void parseNoComma(String sentencePart){
        VerbFT sentence = parseAsClause(sentencePart);
        if( sentence == null ){
            //TODO prettyPrint as other elements of the sentence such as appositive

        }
    }

    private static VerbFT parseAsClause(String sentencePart) {

        SenseFT predict = findPredicate(sentencePart);

        return null;
    }

    private static SenseFT findPredicate(String sentencePart) {
        //Word predict = new Word();
        String[] words = sentencePart.split("\\s");

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            for( String wordStr : words) {
                HttpGet httpget = new HttpGet("http://dict.youdao.com/w/" + wordStr);
                System.out.println("executing request " + httpget.getURI());
                // 执行get请求.
                CloseableHttpResponse response = httpclient.execute(httpget);
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
                } finally {
                    response.close();
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
