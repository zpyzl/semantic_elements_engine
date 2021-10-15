package com.zpy.yearn.yearnTree;

import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeFactory;
import lombok.Data;

import java.util.List;

/**
 * Created by zpy on 2018/9/5.
 */

public class YearnTree extends Tree {
    private Label label;
    private List<YearnTree> children;
    private Tree tree;
    public YearnTree() {
    }
    public YearnTree(Tree tree){
        this.tree = tree;
    }

    public List<YearnTree> getChildren() {
        return children;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public void setLabel(Label label) {
        this.label = label;
    }
    /* public YearnTree(Tag label, List<Tree> children) {
        this.label = label;
        this.children = children;
    }*/

    @Override
    public void setChildren(List<? extends Tree> childTreesList) {
        this.children = (List<YearnTree>) childTreesList;
    }

    @Override
    public Tree[] children() {
        return children.toArray(new Tree[children.size()]);
    }

    @Override
    public TreeFactory treeFactory() {
        return (TreeFactory) new YearnTreeFactory();
    }

    public String getTag() {
        return label.value();
    }

    //private Tree tree;
   // private Tree parent;
   // private StringBuilder prettyPrintSb ;
    //private Clause clause;
    //private boolean visited = false;
    //
/*
    public Tree() {
    }
    public Tree(Clause sentence) {
        //this.clause = sentence;
    }

    public Tree(Tree tree) {
        this.tree = tree;
    }*/
/*

    @Override
    public boolean isLeaf() {
        return tree.isLeaf();
    }

    @Override
    public String toString(){
        return tree.toString() +
                "\nparent:\n{ " + parent + " }";
    }
*/

/*
    public Tag label() {
        return this.tree.label();
    }

    @Override
    public Tree[] children() {
        return getChildren();
    }

    @Override
    public Tree parent() {
        return getParent();
    }
    public List<Tree> siblings(){
        return super.siblings(this.tree);
    }*/
}
