/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EsRepository.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.techFacilities.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zpy on 2018/9/13.
 */
@Component
public class EsRepository {
    private static final Logger logger = LoggerFactory.getLogger(EsRepository.class);

    public static final String LOCAL_ES_URL = "http://localhost:9200/";

    @Resource
    private HttpClient httpClient;

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /*
    POST dict/_doc/use
{
  "word":["用","使用","采用"],
  "trans":"use",
  "predicateProperty":"way",
  "content":"apply obj"
}
     */
    /*public void addSense(List<String> words, Class clazz) throws YearnException {
        SenseClassMap senseClassMap = new SenseClassMap();
        senseClassMap.setClazz(clazz.getName());
        String[] wordArray = new String[words.size()];
        senseClassMap.setWords(words.toArray(wordArray));
        String body = JSON.toJSONString(senseClassMap);
        logger.debug("add sense post body: {}",body);

        String url = EsRepository.LOCAL_ES_URL + "dict/_doc/" + clazz.getSimpleName();
        HttpEntity entity = httpClient.post(url, body);
        Map respMap = getRespMap(entity);
        String result = (String) respMap.get("result");
        if( !"updated".equals(result)){
            throw new YearnException("failed to add sense to es! class "+ clazz.getName());
        }
    }

    public List<JSONObject> queryMultiTerm(Map<SenseField,String> terms) throws YearnException {
        Query query = new Query();

        List<Must> musts = new ArrayList<>();
        Iterator<SenseField> it = terms.keySet().iterator();
        while(it.hasNext()){
            SenseField senseField = it.next();
            String key = senseField.toString();
            musts.add(new Must(new Match(key,terms.get(senseField))));
        }
        Bool bool = new Bool();
        bool.setMust(musts);
        query.setBool(bool);

        return doSearch(query);
    }

    public List<JSONObject> queryByTerm(SenseField termName, String termValue) throws YearnException {
        
        "query": {
    "bool": {
      "must": [
        { "match": { "word": "你" }}
      ]
    }
  }
         
        Query query = new Query();
        Term term = new Term();
        term.put(termName.toString(),termValue);
        query.setTerm(term);

        return doSearch(query);
    }

    private List<JSONObject> doSearch(Query query) throws YearnException {
        SearchBody searchBody = new SearchBody();
        searchBody.setQuery(query);
        String url = EsRepository.LOCAL_ES_URL + "dict/_search";
        String body = JSON.toJSONString(searchBody);
        logger.debug("search body: {}",body);
        HttpEntity entity = httpClient.post(url, body);

        return getDictItem(entity);
    }*/

    private List<JSONObject> getDictItem(HttpEntity entity) throws YearnException {
        Map respMap = getRespMap(entity);
        JSONArray hits = (JSONArray) ((JSONObject) respMap.get("hits")).get("hits");
        if( hits.isEmpty()){
            return null;
        }else {
            List<JSONObject> res = new ArrayList<>();
            for( Object hit: hits) {
                res.add( (JSONObject)((JSONObject)hit).get("_source"));
            }
            return res;
        }
    }

    private Map getRespMap(HttpEntity entity) throws YearnException {
        String respStr = null;
        try {
            respStr = EntityUtils.toString(entity);
        } catch (IOException e) {
            throw new YearnException("failed to convert resp entity,{}", e);
        }
        return JSON.parseObject(respStr, HashMap.class);
    }

}
