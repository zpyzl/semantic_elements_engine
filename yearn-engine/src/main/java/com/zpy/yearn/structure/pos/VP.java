/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VP.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.Tag;

import java.util.HashSet;
import java.util.Set;

import static com.zpy.yearn.pennTree.SyntacPhrase.VP;
import static com.zpy.yearn.pennTree.SyntacPhrase.VPT;

/**
 * Created by zpy on 2019/3/31.
 */
public class VP {
    private static Set<Tag> set = new HashSet<>();
    static {
        set.add(VP);
        set.add(VPT);
    }
    public static Set<Tag> set(){
        return set;
    }
}
