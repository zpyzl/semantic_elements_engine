/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Book.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.corpus;

import java.util.List;

/**
 * Created by zpy on 2018/9/12.
 */
public class Book {
    private String title;
    private Author author;
    private List<BookPart> parts;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<BookPart> getParts() {
        return parts;
    }

    public void setParts(List<BookPart> parts) {
        this.parts = parts;
    }
}
