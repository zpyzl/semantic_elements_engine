package com.zpy.yearn.pennTree;

import com.zpy.yearn.yearnTree.YearnTreeFactory;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by zpy on 2018/9/1.
 */
public class TreeTraverser {
    private static final Logger logger = LoggerFactory.getLogger(TreeTraverser.class);

    public static String prettyPrintTree(Tree pennTree){
        TreeTraverseContext treeTraverseContext = new TreeTraverseContext();
        traverseTreeNode(pennTree, treeTraverseContext,(tree)-> null,
                (tree,nodeString ) -> null);
        return treeTraverseContext.getPrettyPrint().toString();
    }


    public static Tree traverseTreeNode(Tree pennTree, TreeTraverseContext treeTraverseContext,Function<Tree,Tree> leafFunction, BiFunction<Tree, List<Tree>, Tree> nodeFunction) {
        return traverseTreeNode( pennTree, treeTraverseContext, label -> (label.value() == null) ? "": label.value(), leafFunction,nodeFunction);
    }

    private static Tree traverseTreeNode(Tree pennTree, TreeTraverseContext treeTraverseContext,
    Function<Label,String> labelFormatter,
                                         Function<Tree,Tree> leafFunction, BiFunction<Tree,List<Tree>, Tree> nodeFunction) {
        Tree tree = traverseTreeNode(pennTree, treeTraverseContext, 0, false, false, false, true, labelFormatter, leafFunction, nodeFunction);
        //System.out.println(treeTraverseContext.getPrettyPrint());
       // System.out.println(treeTraverseContext.getPrettyPrint().toString().replace("\n",""));
        return tree;
    }

    private static Tree traverseTreeNode(Tree pennTree, TreeTraverseContext context, int indent, boolean parentLabelNull, boolean firstSibling, boolean leftSiblingPreTerminal, boolean topLevel, Function<Label, String> labelFormatter,
                                         Function<Tree,Tree> leafFunction, BiFunction<Tree,List<Tree>, Tree> nodeFunction) {
        context.incrementDepth();
        StringBuilder sb = context.getPrettyPrint();
        // the condition for staying on the same line in Penn Treebank
        boolean suppressIndent = (parentLabelNull || (firstSibling && pennTree.isPreTerminal())
                || (leftSiblingPreTerminal && pennTree.isPreTerminal() && (pennTree.label() == null || !pennTree.label().value().startsWith("CC"))));
        if (suppressIndent) {
            sb.append("      ");
        } else {
            if (!topLevel) {
                sb.append("\n");
            }
            for (int i = 0; i < indent; i++) {
                sb.append("      ");
            }
        }
        if (pennTree.isLeaf() || pennTree.isPreTerminal()) {
            String leafString = TreeF.appendLeafString(pennTree, sb, labelFormatter,logger);
            //
            //Tree tree = leafFunction.apply(pennTree);
            Tree tree = YearnTreeFactory.newLeaf(pennTree);
            return tree;
        }
        printNode(pennTree, sb, labelFormatter);

        //访问节点的地方，儿子内容还没访问。generate the corresponding node in yearn tree
        //
        boolean parentIsNull = pennTree.label() == null || pennTree.label().value() == null;
        List<Tree> children = traverseChildren(pennTree, context, pennTree.children(), indent + 1, parentIsNull, labelFormatter, leafFunction, nodeFunction);

        Tree tree = YearnTreeFactory.newTreeNode(pennTree, children);// nodeFunction.apply(pennTree, children);
        //tree.setChildren(children);
        sb.append(")");
        return tree;
    }


    private static String printNode(Tree pennTree, StringBuilder sb, Function<Label, String> labelFormatter) {
        sb.append("(");
        String label = labelFormatter.apply(pennTree.label());
        sb.append(label);
       // logger.debug(label);
        return label;
    }



    private static List<Tree> traverseChildren(Tree pennTreeParent, TreeTraverseContext treeTraverseContext, Tree[] trChildren, int indent, boolean parentLabelNull, Function<Label,String> labelFormatter, Function<Tree,Tree> leafFunction, BiFunction<Tree ,List<Tree>,Tree> nodeFunction) {

        boolean firstSibling = true;
        boolean leftSibIsPreTerm = true;  // counts as true at beginning
        List<Tree> res = new ArrayList<>();
        for (Tree tree : trChildren) {
            res.add(traverseTreeNode(tree, treeTraverseContext, indent, parentLabelNull, firstSibling, leftSibIsPreTerm, false, labelFormatter,leafFunction, nodeFunction));
            leftSibIsPreTerm = pennTreeParent.isPreTerminal();
            // CC is a special case for English, but leave it in so we can exactly match PTB3 tree formatting
            if (pennTreeParent.value() != null && pennTreeParent.value().startsWith("CC")) {
                leftSibIsPreTerm = false;
            }
            firstSibling = false;
        }
        return res;
    }




}
