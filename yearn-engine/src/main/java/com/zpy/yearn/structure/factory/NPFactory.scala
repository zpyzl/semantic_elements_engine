/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NPFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.other.{DictUtil, Twp}
import com.zpy.yearn.pennTree.SyntacPhrase
import com.zpy.yearn.structure.pos.{Constituent, NounTag}
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/10/17.
  */
object NPFactory extends ThingFactory {
  final private val logger = LoggerFactory.getLogger(NPFactory.getClass)

  override def getIntroNode(npTree: Tree): Array[(Tree,Boolean)] = {
    val ccs = npTree.children.filter( _.isCC)//连词
    if( ccs.nonEmpty){
      npTree.children.filter( ( kid: Tree) =>
        !kid.isCC &&
        ( (kid.isPreTerminal && NounTag.values().contains( kid.wordTag) ) || (kid.isPhrase && kid.syncTag == SyntacPhrase.NP) ) )
        .map( npT => (npT.getLastWord,false)) //过滤出不是连词且是NP（或名词）的
    }else {
      Array((npTree.getLastWord, false))
    } /*else{
      npTree.children.toStream.filterNot(TreeF.isCC)(ccPartIndex)
    }*/
  }

  override def verifyIntroTag(introNode: Tree): Boolean = {
    try return NounTag.contains(introNode.wordTag)
    catch {
      case e: YearnException =>
        logger.error(introNode + " is not a word", e)
    }
    false
  }

  @throws[YearnException]
  override def makeIntroSense(twps: Array[Twp]): Things = {
    for( twp <- twps) yield {
      if (!verifyIntroTag(twp.tree)) logger.warn("Last node of the noun phrase is not a noun! tree: " + twp.tree)
      DictUtil.createNoun(twp).meaning
    }
    /*if( twp.phraseTree.children.toStream.contains((kid: Tree)=> TreeF.getWordTag(kid).equals(WordTag.CC))){//NP中包含CC（连词）
      DictUtil.createCC()
      thingFT
    }*/
  }

  def createSbj(npTree: Tree, twp: Twp): Things = create(twp.copy(tree = npTree, phraseTree = npTree, phraseConstituent = Constituent.SUBJECT, phraseType = SyntacPhrase.NP, isMainStcVerb = false))

  /* private Thing parseCentral(Tree centralTree, Tree root) throws YearnException {
          return parseIfNoun(centralTree,root, predicate);
  *//*Tree nounTree = TreeF.filterByWordTag(tree.children(), NounsSet.set(), "2 nouns found in tree " + tree);*//*if( tree.isPreTerminal()){
              return parseIfNoun(tree,root);
          }else {
              //获取最后一个节点，如果是名词，返回
              return parseCentral(tree.lastChild(),root);
          }*//* 解析名词，就是实例化sense对象
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
    * 解析定语
    *
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
                  parseModifiers(noneDegs.get(0).children(), twp);
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
    *
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
    *
    */
  /*private void parseLeafModifier(Tree tree, Sense sense, Entity entity) {
          if( TreeF.getWordTag(tree).equals(WordTag.JJ)){
              Adj adj = yearnWordService.createModifierSenseObject(tree, sense, entity, predicate, constituent);
              entity.properties().add( adj);
          }
      }*/
}