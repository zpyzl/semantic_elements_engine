package com.zpy.yearn.yearnTree;

import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.ling.LabelFactory;

/**
 * Created by zpy on 2018/10/31.
 */
public class YearnLabelFactory implements LabelFactory {
    public static Label createLabel(String s){
        Label label = new YearnLabel();
        label.setFromString(s);
        return label;
    }
    @Override
    public Label newLabel(String s) {
        return createLabel(s);
    }

    @Override
    public Label newLabel(String s, int i) {
        return null;
    }

    @Override
    public Label newLabelFromString(String s) {
        return newLabel(s);
    }

    @Override
    public Label newLabel(Label label) {
        return label;
    }
}
