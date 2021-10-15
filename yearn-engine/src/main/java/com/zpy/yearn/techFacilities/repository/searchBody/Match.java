/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Match.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.repository.searchBody;

import java.util.HashMap;

/**
 * Created by zpy on 2018/9/14.
 */
public class Match extends HashMap<String,String> {
    public Match(String k, String v){
        put(k,v);
    }
}
