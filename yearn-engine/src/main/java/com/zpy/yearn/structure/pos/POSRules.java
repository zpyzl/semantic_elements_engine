/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:POSRules.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.WordTag;
import com.zpy.yearn.structure.sense.StaticSense;
import com.zpy.yearn.structure.yearnTree.Tree;

/**
 * Created by zpy on 2018/9/15.
 */
public class POSRules {

    public static String genTreeContent(String word, StaticSense staticSense, String tag, Tree tree){
        WordTag pos = WordTag.valueOf(tag);
        if( pos.equals(WordTag.PN)){
            //tree.
           //  sense.getVarType()
        }

        return null;
    }

    public static boolean contains(POS pos, WordTag wordTag) {
        //if( POS.N)
        return false;
    }
}
