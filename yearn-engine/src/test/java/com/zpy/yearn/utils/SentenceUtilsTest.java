/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SentenceUtilsTest.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.common.YearnException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by zpy on 2018/8/6.
 */
public class SentenceUtilsTest {

    @Test
    public void testGetWords() throws YearnException {
        Set<String> wordStrs = SentenceUtils.toDistinctWordStrs("This is a white boy, with  a   white cat.");
        Assert.assertTrue(wordStrs.contains("This"));
        Assert.assertTrue(wordStrs.contains("is"));
        Assert.assertTrue(wordStrs.contains("a"));
        Assert.assertTrue(wordStrs.contains("white"));
        Assert.assertTrue(wordStrs.contains("boy"));
        Assert.assertTrue(wordStrs.contains("with"));
        Assert.assertTrue(wordStrs.contains("cat"));
    }
    @Test
    public void testGetSentListStr() {
        List<String> sentenceStrList = SentenceUtils.getSentenceStrList("一句话， 又一句话。 第三句话。");
        Assert.assertEquals("一句话", sentenceStrList.get(0).trim());
        Assert.assertEquals("又一句话", sentenceStrList.get(1).trim());
        Assert.assertEquals("第三句话", sentenceStrList.get(2).trim());
    }
    @Test
    public void testGetSentListStrEn(){
        List<String> sentenceStrList2 = SentenceUtils.getSentenceStrList("one. two .  tree. ");
        Assert.assertEquals("one",sentenceStrList2.get(0).trim());
        Assert.assertEquals("two",sentenceStrList2.get(1).trim());
        Assert.assertEquals("tree",sentenceStrList2.get(2).trim());

    }
}
