/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DictInitializerTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.repo;

import com.zpy.yearn.techFacilities.repository.EsRepository;

import javax.annotation.Resource;

/**
 * Created by zpy on 2018/11/7.
 */
public class DictInitializerTest {//extends TestBase {
    @Resource
    private EsRepository esRepository;
   /* @Test
    public void testInit() throws YearnException {

        List<JSONObject> jsonObjects = esRepository.queryByTerm(SenseField.clazz, "Basis");
        Assert.assertTrue(jsonObjects.size() == 1);
    }*/
}
