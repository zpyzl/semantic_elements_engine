/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounsSet.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.Tag;
import com.zpy.yearn.pennTree.WordTag;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zpy on 2018/11/19.
 */
public class NounsSet {
    private static Set<Tag> set = new HashSet<>();
    static {
        for( NounTag nounTag: NounTag.values() ){
            set.add(WordTag.valueOf(nounTag.toString()));
        }
    }
    public static Set<Tag> set(){
        return set;
    }

}
