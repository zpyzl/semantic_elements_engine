package com.zpy.yearn.pennTree;

import com.zpy.yearn.common.YearnException;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by zpy on 2018/10/21.
 */
public class TreeF {
    private static final Logger logger = LoggerFactory.getLogger(TreeF.class);

    /**
     * last leaf is word without tag!
     *
     * @param tree
     * @return
     */
    public static Tree getLastWordNoTag(Tree tree) {
        List<Tree> leaves = tree.getLeaves();
        return leaves.get(leaves.size() - 1);
    }


    /**
     * 按label过滤，如果有两个则抛异常
     *
     * @param trees
     * @param tags
     * @param errorMsg 异常信息
     * @return
     * @throws YearnException
     */
   /* public static Tree filterByWordTag(List<Tree> trees, Set<Tag> tags, Function<> multiHandler, String errorMsg) throws YearnException {
        List<Tree> matched = trees.stream().filter(tree ->
                {
                    try {
                        return tree.isPreTerminal() &&
                       tags.contains(TreeF.getWordTag(tree));
                    } catch (YearnException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
        ).collect(Collectors.toList());
        if (!matched.isEmpty()) {
            if (matched.size() > 1) {
                multiHandler.apply();
                throw new YearnException(errorMsg);
            }else {
                return matched.get(0);
            }
        }
        return null;
    }*/

    public static String appendLeafString(Tree tree, StringBuilder sb, Function<Label, String> labelFormatter, Logger logger) {
        String leafString = tree.toStringBuilder(new StringBuilder(), labelFormatter).toString();
        //logger.debug(leafString);
        sb.append(leafString);
        return leafString;
    }

    public static String getLeafString(Tree tree) {
        String leafString = tree.toStringBuilder(new StringBuilder(), label -> (label.value() == null) ? "" : label.value()).toString();
        return leafString;
    }
    public static Leaf getNecessaryLeaf(Tree tree) throws YearnException {
        if( !tree.isPreTerminal()){
            throw new YearnException(tree + " is not a leaf!");
        }
        return getLeaf(tree);
    }
    public static String getWord(Tree tree){
        return getLeaf(tree).getWord();
    }
    public static String getTag(Tree tree){
        return getLeaf(tree).getTag();
    }
    public static Leaf getLeaf(Tree tree) {
        if( !tree.isPreTerminal()){
            //logger.warn("not a leaf: "+tree.toString());
        }
        return new Leaf(getLeafString(tree)).invoke();
    }
    /*要么获取非叶节点syntac tag 要么获取叶节点word tag
    public static Tag getTag(Tree tree) {
        try {
            return WordTag.valueOf(getLeaf(tree).getTag());
        }catch (IllegalArgumentException e){
            return SyntacPhrase.valueOf(getLeaf(tree).getTag());
        }
    }*/

    public static WordTag getWarnedWordTag(Tree tree) {
        try {
            return WordTag.valueOf(getLeaf(tree).getTag());
        }catch (IllegalArgumentException e ){
            //logger.warn(e.getMessage());
        }
        return null;
    }
    public static WordTag getWordTag(Tree tree) throws YearnException {
        return WordTag.valueOf(getNecessaryLeaf(tree).getTag());
    }



    /* warn 吞异常不对
    public static Option<SyntacPhrase> getSyncTag(Tree tree) {
        try {
            return new Some(SyntacPhrase.valueOf(tree.value()));
        } catch (IllegalArgumentException e) {
            logger.warn("In getSyncTag No enum value: "+tree.value());
        }
        return scala.Option.apply(null);
    }*/


}
