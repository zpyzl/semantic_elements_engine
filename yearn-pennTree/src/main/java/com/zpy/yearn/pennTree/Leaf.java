package com.zpy.yearn.pennTree;

import org.springframework.util.Assert;

/**
 * Created by zpy on 2018/10/24.
 */

public class Leaf {
    private String leafString;
    private String tag;
    private String word;

    public Leaf(String leafString) {
        this.leafString = leafString;
    }

    public String getTag() {
        return tag;
    }
    public WordTag getWordTag(){
        return WordTag.valueOf(tag);
    }

    public String getWord() {
        return word;
    }

    public Leaf invoke() {
        String[] split = leafString.split(" ");
        tag = split[0];
        tag = tag.substring(1);
        word = split[1];
        word = word.substring(0,word.length() - 1);
        return this;
    }
}