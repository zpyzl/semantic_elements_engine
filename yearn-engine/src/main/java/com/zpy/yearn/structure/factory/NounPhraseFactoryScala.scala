/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NounPhraseFactoryScala.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.zpy.yearn.service.YearnWordService
import javax.annotation.Resource
import org.springframework.stereotype.Component

/**
  * Created by zpy on 2018/10/17.
  */
@Component class NounPhraseFactoryScala {//extends ThingFactory {
  @Resource private val yearnWordService:YearnWordService = null
/*

  @throws[YearnException]
  override def createOther(phraseTwp: Twp, thing: Thing): Unit = {
    val treeList = phraseTwp.tree.children
    val modifiersTrees = new util.ArrayList[Tree](treeList)
    modifiersTrees.remove(treeList.size - 1) //去掉最后一个名词就是定语
    val staticSenses = parseModifiers(modifiersTrees, phraseTwp)
    import scala.collection.JavaConversions._
    for (staticSense <- staticSenses) {

      thing.properties += new Be(thing, staticSense)形容词用be,数量设置，特指。。
    }
  }

  override def getIntroNode(tree: Tree): Tree = TreeF.getLastWord(tree)

  override def verifyIntroTag(introNode: Tree): Boolean = NounTag.contains(TreeF.getWordTag(introNode))

  @throws[YearnException]
  override def makeIntroSense(twp: Twp): Thing = if (verifyIntroTag(twp.tree)) yearnWordService.createThingObject(twp).asInstanceOf[Thing]
  else throw new YearnException("Last node of the noun phrase is not a noun!")

  /**
    *
    * 解析定语
    *
    * @param modifierTrees
    * @param twp
    * @return
    * @throws YearnException
    */
  @throws[YearnException]
  private def parseModifiers(modifierTrees: util.List[Tree], twp: Twp) = {
    val mods = new util.ArrayList[StaticSense]
    //找出“的”
    val degs = modifierTrees.stream.filter((tree: Tree) => TreeF.getWordTag(tree) == WordTag.DEG).collect(Collectors.toList)
    //非“的”
    val noneDegs = modifierTrees.stream.filter((tree: Tree) => !(TreeF.getWordTag(tree) == WordTag.DEG)).collect(Collectors.toList)
    if (noneDegs.size == 1) { //除了“的”,如果只有一个树，递归
      if (noneDegs.get(0).isLeaf) {
        val modifierTree = noneDegs.get(0)
        twp.tree_$eq(modifierTree)
        mods.add(yearnWordService.createModifierSenseObject(twp))
        //返回一个modifer，设置给名词
      }
      else parseModifiers(noneDegs.get(0).children, twp)
    }
    else { //针对不同类型短语去解析定语
      import scala.collection.JavaConversions._
      for (tree <- noneDegs) {
        twp.tree_$eq(tree)
        mods.add(yearnWordService.createModifierSenseObject(twp))
      }
    }
    mods
  }

  /**
    * ADJP,DNP,QP,CP
    *
    * @param tree
    * @param staticSense
    * @param thing
    */
  private def parseNoneLeafModifier(tree: Tree, staticSense: StaticSense, thing: Entity) = if (TreeF.getWordTag(tree) == SyntacPhrase.QP) parseQP(staticSense)

  private def parseQP(staticSense: StaticSense) = {
  }
*/

  /**
    * JJ,N
    *
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