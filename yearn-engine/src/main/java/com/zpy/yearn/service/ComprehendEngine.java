/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ComprehendEngine.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service;

import com.zpy.yearn.dict.meta.predicate.fromTree.VerbFT;
import org.springframework.stereotype.Component;

/**
 * Created by zpy on 2018/11/13.
 */
@Component
public class ComprehendEngine {

    /**
     * 理解action。
     * @param source IB内在的知识
     * @param target IB要理解的句子
     */
    public void comparePredicate(VerbFT source, VerbFT target){
       /* if( target instanceof source.getClass()
            && target.getSbj instanceof source.getSbj
            && obj
            && compare cause){
            //行为、主语、宾语都符合

        }*/
    }
    /*compareCause(){
        // if np

        //if pred
    }
    compareNP(){
        // compare central
        // compare modifier
    }*/
}
