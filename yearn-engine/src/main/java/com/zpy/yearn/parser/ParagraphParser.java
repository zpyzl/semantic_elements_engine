/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ParagraphParser.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.parser;

import com.zpy.yearn.dict.meta.predicate.fromTree.VerbFT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpy on 2018/7/23.
 */
public class ParagraphParser {

    public static List<VerbFT> parse(String paragraph){
        String[] sentenceStrs = paragraph.split("\\.");
        List<VerbFT> sentences = new ArrayList<VerbFT>();
        for( String sentenceStr: sentenceStrs) {
            VerbFT sentence = SentenceParser.parse(sentenceStr);
            sentences.add(sentence);
        }
        return sentences;
    }
}
