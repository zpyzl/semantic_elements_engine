/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SentenceUtils.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.common.EnMarkConstants;
import com.zpy.yearn.common.RegexConstants;

import java.util.*;

/**
 * Created by zpy on 2018/8/6.
 */
public class SentenceUtils {

    /**
     * 多个句子的字符串转换成句子列表
     * @param sentencesStr
     * @return
     */
    public static List<String> getSentenceStrList(String sentencesStr){
        sentencesStr = sentencesStr.replaceAll("\\s","");

        String splitter;
        if( sentencesStr.contains(".")) {
            splitter = "\\.";
        }else {
            splitter = "。|，";
        }
        String[] sentences = sentencesStr.split(splitter);
        return new ArrayList<>(Arrays.asList(sentences));
    }


    public static Set<String> toDistinctWordStrs(String sentences) {
        sentences = sentences.trim();

        String[] words = sentences.split("["+EnMarkConstants.PERIOD+EnMarkConstants.COMMA+RegexConstants.BLANK+"]");
        return new HashSet<>(Arrays.asList(words));
    }
}
