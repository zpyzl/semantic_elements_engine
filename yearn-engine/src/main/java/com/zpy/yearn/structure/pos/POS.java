/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:POS.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.pos;

import com.zpy.yearn.pennTree.WordTag;

/**
 * Created by zpy on 2018/9/17.
 */
public enum POS {
    N,V,ADJ,;
    /*
    private WordTag wordTag;

    POS(WordTag wordTag){
        this.wordTag = wordTag;
    }*/
    public boolean contains(WordTag wordTag){
        //todo POS包含哪些tag
        return true;
    }
}
