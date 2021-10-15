/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EsTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.repo;

import com.zpy.yearn.techFacilities.repository.EsRepository;

/**
 * Created by zpy on 2018/9/14.
 */
public class EsTest {
    EsRepository esRepository = new EsRepository();
   /* @Before
    public void init(){
        HttpClient httpClient = new HttpClient();
        httpClient.setHttpClient(HttpClients.createDefault());
        esRepository.setHttpClient(httpClient);
    }
    @Test
    public void testEsGet() throws IOException, YearnException {

        List<JSONObject> jsonObject = esRepository.queryByTerm(SenseField.word, "most");
        print(jsonObject);
        Assert.assertTrue(!jsonObject.isEmpty());
    }
    @Test
    public void testMultiTerm() throws YearnException {
        Map<SenseField,String> fieldMap = new HashMap<SenseField,String>();
        fieldMap.put(SenseField.word, "自己");
        fieldMap.put(SenseField.tag, "PN");

        List<JSONObject> jsonObject = esRepository.queryMultiTerm(fieldMap);
        print(jsonObject);

        Assert.assertTrue(!jsonObject.isEmpty());

    }*/
}
