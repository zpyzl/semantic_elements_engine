/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounTag.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.WordTag;

/**
 * Created by zpy on 2018/10/28.
 */
public enum NounTag {
    PN,NN,NR,NT;
    public static boolean contains(WordTag wordTag){
        for( NounTag nt: values()){
            if( nt.toString().equals(wordTag.toString())){
                return true;
            }
        }
        return false;
    }
}
