/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BookPart.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.corpus;

import com.zpy.yearn.dict.meta.hasArgs.LinkV;

import java.util.List;

/**
 * Created by zpy on 2018/9/12.
 */
public class BookPart {
    private String title;
    private String content;
    //private Verb sentence;
    private LinkV concept;
    private List<BookPart> parts;

    public BookPart(String title) {
        this.title = title;
    }

    public BookPart(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public LinkV getConcept() {
        return concept;
    }

    public void setConcept(LinkV concept) {
        this.concept = concept;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public List<BookPart> getParts() {
        return parts;
    }

    public void setParts(List<BookPart> parts) {
        this.parts = parts;
    }
}
