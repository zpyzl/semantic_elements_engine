/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Definition.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.knowledge;


import com.zpy.yearn.dict.meta.hasArgs.TPred;
import com.zpy.yearn.structure.yearnTree.Tree;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpy on 2018/9/12.
 */
@Data
public class Definition {
    private List<String> contents = new ArrayList<>();
    private List<TPred> contentVerbs = new ArrayList<>();
    private List<Tree> contentTrees = new ArrayList<>();

    public Definition(Tree defTree) {
        this.contentTrees.add(defTree);
    }

    public Definition(String definitionStr) {
        this.contents.add(definitionStr);
    }
}
