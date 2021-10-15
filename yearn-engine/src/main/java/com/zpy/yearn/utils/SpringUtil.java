/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SpringUtil.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.utils;

import com.zpy.yearn.facade.Chatbot;
import com.zpy.yearn.service.YearnWordService;
import com.zpy.yearn.structure.factory.MainPredicateFactory;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zpy on 2018/11/22.
 */
public class SpringUtil {
    private static ConfigurableApplicationContext applicationContext;

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
    public static Object getBean(Class clazz){
        Object bean = applicationContext.getBean(clazz);
        return bean;
    }
    public static Chatbot getChatbot(){
        return (Chatbot) getBean(Chatbot.class);
    }
    public static YearnWordService getWordService(){
        return (YearnWordService) getBean(YearnWordService.class);
    }
    /*public static NounPhraseFactory getNPFactory(){
        return (NounPhraseFactory) getBean(NounPhraseFactory.class);
    }
    public static NounPhraseFactory getAdjPFactory(){
        return (NounPhraseFactory) getBean(AdjPFactory.class);
    }*/
    public static MainPredicateFactory getPredicateFactory(){
        return (MainPredicateFactory) getBean(MainPredicateFactory.class);
    }
}
