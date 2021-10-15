/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ChSentenceServiceTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.service;

import com.zpy.common.TestBase;
import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.structure.yearnTree.Tree;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.WordTag;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zpy on 2018/9/19.
 */
public class ChSentenceServiceTest{//} extends TestBase {
    @Resource
    private ChSentenceService chSentenceService;

   /* @Test
    public void testYTree() throws YearnException {
        Tree tree  = chSentenceService.stc2Tree("我一无是处");
        //Tree yTree = new Tree(tree);
        Label label1 = new WordTag(tree.word(), com.zpy.yearn.pennTree.WordTag.CC.toString());
        Label corelabel = new CoreLabel();
        corelabel.setValue("BA");
        Tree copy = tree.getLastWord();
        copy.pTree().setLabel(corelabel);

        System.out.println();
    }*/
/*
    @Test
    public void testExtremeEvalSelf() throws YearnException {
        Verb statement = chSentenceService.stc2Clause("你用一种极端的标准评价自己");
        print(JSON.toJSONString(statement,true));


    }
    @Test
    public void testNewYTree(){
        YearnTree yearnTree = new YearnTree();
        print("s"+yearnTree.getChildren());
    }*/
}
