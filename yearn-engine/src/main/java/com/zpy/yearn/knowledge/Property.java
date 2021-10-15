/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Property.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.knowledge;

import com.zpy.yearn.structure.yearnTree.Tree;
import lombok.Data;

import java.util.List;

/**
 * Created by zpy on 2018/9/12.
 */
@Data
public class Property {
    private List<Tree> content;
    private List<Example> examples;
}
