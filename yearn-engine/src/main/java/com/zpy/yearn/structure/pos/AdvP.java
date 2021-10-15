/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdvP.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.Tag;

import java.util.HashSet;
import java.util.Set;

import static com.zpy.yearn.pennTree.SyntacPhrase.*;

/**
 * Created by zpy on 2018/10/24.
 */
public class AdvP {
    private static Set<Tag> set = new HashSet<>();
    static {//todo 补充完整
        set.add(PP);
        set.add(DVP);
        set.add(ADVP);
        set.add(LCP);
    }
    public static Set<Tag> set(){
        return set;
    }
}
