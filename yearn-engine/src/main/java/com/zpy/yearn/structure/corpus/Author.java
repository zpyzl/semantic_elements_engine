/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Author.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.corpus;

import lombok.Data;

/**
 * Created by zpy on 2018/9/12.
 */
@Data
public class Author {
    private String name ;

    public Author(String name) {
        this.name = name;
    }
}
