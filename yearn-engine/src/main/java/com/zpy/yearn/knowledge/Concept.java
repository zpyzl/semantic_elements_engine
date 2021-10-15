/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Concept.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.knowledge;

import com.zpy.yearn.dict.meta.hasArgs.TPred;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpy on 2018/9/12.
 */
public class Concept {
    private String name;
    Definition definition;
    //private List<Definition> definitions = new ArrayList<>();
    private List<Property> properties = new ArrayList<>();
    private List<Example> examples = new ArrayList<>();

    public Concept(String name, String defStr) {
        this.name = name;
        setDefinition(defStr);
    }

    public Concept(String name, TPred conceptPred) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setDefinition(String definitionStr){
        Definition definition = new Definition(definitionStr);
        this.definition = definition;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public Concept(String name, Definition definition){
        this.name = name;
        this.definition = definition;
    }

}
