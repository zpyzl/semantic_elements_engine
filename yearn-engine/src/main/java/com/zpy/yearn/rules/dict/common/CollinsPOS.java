/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:CollinsPOS.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.rules.dict.common;

/**
 * Created by zpy on 2018/7/19.
 */
public enum CollinsPOS {
    N_COUNT("N-COUNT","可数名词"),
    N_VAR("N-VAR","可做可数名词,也可做不可数名词的词"),
    VT("V-T","及物动词"),
    VI("V-I","不及物动词"),
    VTVI("V-T/V-I","及物或不及物动词"),
    VIVT("V-I/V-T","及物或不及物动词"),
    ADJ("ADJ","形容词"),;// ADV, PRONOUN, PREP, CONJ, INTERJ,;
//TODO

    private String en;
    private String ch;

    private CollinsPOS(String en, String ch){
        this.en = en;
        this.ch = ch;
    }

    public static CollinsPOS getPartOfSpeechByEn(String en){
        for( CollinsPOS collinsPOS : CollinsPOS.values()){
            if( collinsPOS.en.equals(en)){
                return collinsPOS;
            }
        }
        return null;
    }
}
