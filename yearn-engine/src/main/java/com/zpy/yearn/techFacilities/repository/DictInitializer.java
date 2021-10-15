/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DictInitializer.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.techFacilities.repository;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.dict.meta.other.Sense;
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap;
import com.zpy.yearn.dict.meta.pred.PredPairInfer;
import com.zpy.yearn.factory.VerbFactoryBase;
import com.zpy.yearn.structure.sense.StaticSense;
import com.zpy.yearn.utils.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zpy on 2018/11/7.
 */
public class DictInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DictInitializer.class);

    private static Map<String, List<Class>> staticSenseDict = new HashMap<>();
    private static Map<String, List<Class>> senseDict = new HashMap<>();

    public Map<String, List<Class>> getStaticSenseDict() {
        return staticSenseDict;
    }
    public Map<String, List<Class>> getSenseDict() {
        return senseDict;
    }

    public static String DICT_PACKAGE_NAME = "com.zpy.yearn.dict";

    public static void initDict() throws YearnException {

        List<Class<?>> classes = ClassUtil.getClasses(DICT_PACKAGE_NAME);
        for( Class<?> staticSenseClazz : classes){
            if( Arrays.stream(staticSenseClazz.getInterfaces()).map(Class::getName).collect(Collectors.toList()).contains( StaticSense.class.getName())  ) {
                try {
                    Method wordsMethod = staticSenseClazz.getDeclaredMethod("words");
                    Constructor<?> constructor = staticSenseClazz.getDeclaredConstructors()[0];
                    constructor.setAccessible(true);
                    String wordsStr = (String)
                            wordsMethod.invoke(constructor.newInstance());
                    String[] words = wordsStr.split("\\s");

                    String wordClassName = staticSenseClazz.getName().substring(0,staticSenseClazz.getName().length() -1 );
                    Class senseClass = null;
                    try {
                        senseClass = Class.forName(wordClassName);
                    } catch (ClassNotFoundException e) {
                        throw new YearnException("failed to find class name "+ wordClassName,e);
                    } catch (Throwable throwable){
                        throwable.printStackTrace();
                    }
                    for( String word:words){
                        // 根据不同的词性类，放入不同的MAP
                        SenseClassMap.put(word, (Class<? extends Sense>) senseClass);

                        putClass(staticSenseClazz, word, staticSenseDict);
                        putClass(senseClass, word,senseDict);
                    }
                } catch ( NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                    throw new YearnException("no words field for the sense class "+staticSenseClazz.getName(),e);
                } catch (IllegalAccessException e) {
                    throw new YearnException("failed to get words of the class "+staticSenseClazz.getName(),e);
                }
            }else if( staticSenseClazz.getSuperclass() == PredPairInfer.class ){

            }
            //throw new YearnException("failed to init dict item class {}", clazz.getName());
        }

    }


    private static void putClass(Class wordClass, String word, Map<String, List<Class>> dict) {
        List<Class> staticSenseClassList;
        if( dict.containsKey(word)){
            staticSenseClassList = dict.get(word);
            staticSenseClassList.add(wordClass);
        }else {
            staticSenseClassList = new LinkedList<>();
            staticSenseClassList.add(wordClass);
            dict.put(word,staticSenseClassList);
        }
    }
}
