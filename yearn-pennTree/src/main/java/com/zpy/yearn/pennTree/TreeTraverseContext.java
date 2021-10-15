package com.zpy.yearn.pennTree;

import lombok.Data;

/**
 * Created by zpy on 2018/9/21.
 */
public class TreeTraverseContext {
    private StringBuilder prettyPrint = new StringBuilder();
    //private Tree clauseTree;
    private int depth = 0;
    //private Tree parentYTree;
    //private Stack<ToSetClause> toSets;

    public StringBuilder getPrettyPrint() {
        return prettyPrint;
    }

    public void setPrettyPrint(StringBuilder prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    public void incrementDepth(){
        this.depth++;
    }
    /*public Tree getParentTree(){
        return this.getParentYTree().getTree();
    }*/
}
