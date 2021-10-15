/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ConceptMatcher.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.rules.knowledgeConnect;

import com.zpy.yearn.pennTree.TreeTraverser;
import org.springframework.stereotype.Component;

/**
 * 句子间概念的匹配
 * Created by zpy on 2018/9/1.
 */
@Component
public class ConceptMatcher {

    public void match(TreeTraverser source, TreeTraverser target){
        //优先级：形容词、副词、名词、动词
    }
}
