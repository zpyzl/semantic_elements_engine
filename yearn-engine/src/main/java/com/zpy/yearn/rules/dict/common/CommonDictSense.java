/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:CommonDictSense.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.rules.dict.common;

import lombok.Data;

import java.util.List;

/**
 * 一般的词典中一个单词的一个义项
 * Created by zpy on 2018/7/19.
 */
@Data
public class CommonDictSense {
    private int number;
    private CollinsPOS collinsPOS;
    private String enExplain;
    private String chExplain;
    private List<Example> examples;

    //private String str;
}
