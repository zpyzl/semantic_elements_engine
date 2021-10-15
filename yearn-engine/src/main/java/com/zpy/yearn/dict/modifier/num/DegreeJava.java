/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DegreeJava.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.num;

/**
 * Created by zpy on 2019/5/7.
 */
public enum DegreeJava {

    EXTREME(90), VERY(80);

    private int num;
    private DegreeJava(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
