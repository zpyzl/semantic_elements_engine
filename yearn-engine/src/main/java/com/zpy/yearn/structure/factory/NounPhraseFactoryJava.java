/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounPhraseFactoryJava.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory;

/**
 * Created by zpy on 2018/10/17.
 */
//@Component
public class NounPhraseFactoryJava{// implements ThingFactory {
    //private final Logger logger = LoggerFactory.getLogger(NPFactory.class);

   // @Resource
    //private YearnWordService yearnWordService;

    /*@Override
    public void createOther(Twp phraseTwp, ThingFT thingFT) {
        //NounFT$.MODULE$.setMods( phraseTwp, ((NounFT)thingFT).noun() );
    }*/
/*
    @Override
    public Tree getIntroNode(Tree tree, Int ccPartIndex) {
        return TreeF.getLastWord(tree);
    }

    @Override
    public boolean verifyIntroTag(Tree introNode) {
        try {
            return NounTag.contains(TreeF.getWordTag(introNode));
        } catch (YearnException e) {
            logger.error(introNode + " is not a word", e);
        }
        return false;
    }

    @Override
    public ThingFT makeIntroSense(Twp twp) throws YearnException {
        if (!verifyIntroTag(twp.tree())) {
            logger.warn("Last node of the noun phrase is not a noun! tree: " + twp.tree());
        }
        return DictUtil.createThingObject(twp);
    }*/

   /* private Thing parseCentral(Tree centralTree, Tree root) throws YearnException {
        return parseIfNoun(centralTree,root, predicate);
*/
    /*Tree nounTree = TreeF.filterByWordTag(tree.childrenList(), NounsSet.set(), "2 nouns found in tree " + tree);*/

        /*if( tree.isPreTerminal()){
            return parseIfNoun(tree,root);
        }else {
            //获取最后一个节点，如果是名词，返回
            return parseCentral(tree.lastChild(),root);
        }*/


   /* 解析名词，就是实例化sense对象
   private Entity parseNoun(Sense sense, Tree tree) {
        Noun noun = new Noun();
        //todo “双重人格”里面的“双重”要测试一下
        *//*if( sense.tag().equals(WordTag.PN)) {
            if (sense.getVarType().equals(VarType.IB)) {
                //todo context中有句中所有代词，遍历所有consituents查看是否有相同的，相同的默认指代一致
                noun = new IntelligentBody();
            }
        }else if( sense.tag().equals(WordTag.NN)) {
            //todo 名词可以分人、物、事（happening）等
        }*//*
        noun.setSense(sense);
        noun.setTree(tree);
        return noun;
    }*/


    /**
     *
     *      解析定语
     * @param modifierTrees
     * @param twp
     * @return
     */
    /*private List<StaticSense> parseModifiers(List<Tree> modifierTrees, Twp twp) {
        //List<StaticSense> mods = new ArrayList<>();
        //找出“的”
       // List<Tree> degs = modifierTrees.stream().filter(tree
      //          -> TreeF.getWordTag(tree).equals(WordTag.DEG)).collect(Collectors.toList());
        //非“的”
        List<Tree> noneDegs = modifierTrees.stream().filter(tree
                -> !(TreeF.getWordTag(tree)).equals(WordTag.DEG)).collect(Collectors.toList());
        if( noneDegs.size() == 1 ) {
            //除了“的”,如果只有一个树，递归
            if( noneDegs.get(0).isLeaf()){
                Tree modifierTree = noneDegs.get(0);
                twp.tree_$eq(modifierTree);
                Twp modTwp = twp.copy(twp.tree(),twp.phraseTree());
                yearnWordService.createModifierSenseObject(twp);
                //返回一个modifer，设置给名词
            }else {
                parseModifiers(noneDegs.get(0).childrenList(), twp);
            }
        }else {
            //针对不同类型短语去解析定语
            for( Tree tree : noneDegs){
                twp.tree_$eq(tree);
                 yearnWordService.createModifierSenseObject(twp);
            }
        }
        return mods;
    }
*/
    /**
     * ADJP,DNP,QP,CP
     * @param tree
     * @param staticSense
     * @param thing
     */
    /*private void parseNoneLeafModifier(Tree tree, StaticSense staticSense, Entity thing) {
        if( TreeF.getWordTag(tree).equals(SyntacPhrase.QP)){
            parseQP(staticSense);

        }
    }

    private void parseQP(StaticSense staticSense) {

    }
*/
    /**
     * JJ,N
     * @param tree
     * @param sense
     * @param entity
     */
    /*private void parseLeafModifier(Tree tree, Sense sense, Entity entity) {
        if( TreeF.getWordTag(tree).equals(WordTag.JJ)){
            Adj adj = yearnWordService.createModifierSenseObject(tree, sense, entity, predicate, constituent);
            entity.properties().add( adj);
        }
    }*/

}
