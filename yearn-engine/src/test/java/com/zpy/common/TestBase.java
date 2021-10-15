/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TestBase.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.common;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap;
import com.zpy.yearn.facade.Chatbot;
import com.zpy.yearn.techFacilities.repository.DictInitializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import yearn.burns.RecognitionTwists;

import javax.annotation.Resource;

/**
 * Created by zpy on 2018/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.zpy.yearn.YearnApplication.class )
public class TestBase  {
    @Resource
    Chatbot chatbot;
    public static void print(Object s){
        System.out.println(s.toString());
    }
    @Before
    public void inputCommonSense() throws YearnException {
        long start = System.currentTimeMillis();
        DictInitializer.initDict();
        long end = System.currentTimeMillis() - start;
        print("init dict cost: " +end+"ms, "+ SenseClassMap.map().size() + " sense classes loaded.");
        /*chatbot.inputCommonSense(
                        */
                //gre p17:
                 /* +"早期的科学家相信北美洲有两种海狸：水坝海狸和岸海狸。他们认为岸海狸在行为上像麝鼠，住在地洞或窝里，不能建造水坝。建造水坝是应对每年水位变化的一种策略。如果水位在夏天下降，那么海狸窝的入口可能被暴露。如果水位稳定，它们的家就安全多了。沿着岸海狸出没的深深的河流，这个问题几乎没有。但是这些海狸知道如何建造水坝。如果这种需求出现了，它们就会这样做，比如在所有附近的树木被砍伐和消失后，它们被迫搬家的时候。"*/


    }

/*
你可能会问，是不是只要标记“没用”和“极端。。评价”这样粗略的相关词语就可以呢？
那么请看：张三说没用的人是可耻的，这可就不是“你用极端的标准评价自己”了
 */
/*
    public void initDict() throws YearnException {

        List<Class<?>> classes = ClassUtil.getClasses(DICT_PACKAGE_NAME);
        for( Class<?> staticSenseClazz : classes){
            if( StaticSense.class.isAssignableFrom( staticSenseClazz)  ) {
                try {
                    Field chStr = staticSenseClazz.getDeclaredField("words");
                    String wordsStr = (String) chStr.get(staticSenseClazz);
                    String[] chWords = wordsStr.split("\\s");

                    String wordClassName = staticSenseClazz.getName().substring(0,staticSenseClazz.getName().length() -1 );
                    for( String word:chWords){
                        WordClassMap.put(word, (Class<? extends Sense>) staticSenseClazz);
                    }
                }  catch (IllegalAccessException e) {
                    throw new YearnException("failed to get chStr of the class "+staticSenseClazz.getName(),e);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(staticSenseClazz.getName() + " has no chStr field!");
                }
            }
            //throw new YearnException("failed to init dict item class {}", clazz.getName());
        }

    }*/

}
