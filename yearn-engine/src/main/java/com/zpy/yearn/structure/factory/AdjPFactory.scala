/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdjPFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.other.{DictUtil, Twp}
import com.zpy.yearn.factory.{PredicateAdjFactory, PredicativeAdjFactory}
import com.zpy.yearn.structure.factory.NPFactory.{logger, verifyIntroTag}
import com.zpy.yearn.structure.pos.AdjTag
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/12/2.
  */
object AdjPFactory  {
  final private val logger = LoggerFactory.getLogger(AdjPFactory.getClass)

  def create(phraseTreeParam: Twp): Array[Adj] = {
    logger.debug(s"phraseTreeParam: \n $phraseTreeParam")
    val introNodes: Array[(Tree,Boolean)] = getIntroNode(phraseTreeParam.tree)
    //intro和短语的predicate、成分、短语类型一样， intro的intro是短语的引导词
    val introTwps = getIntroTwp(phraseTreeParam, introNodes)

    //状语函数在谓语sense创建后应用 ~~状语中的介宾短语需要先创建好，以传给谓语做宾语参数，所以先createOther
    //val otherTwp = createOtherTwp(phraseTreeParam, introFT.meaning)
    //createOther(phraseTreeParam)
    makeIntroSense(introTwps)
  }
  def getIntroTwp(phraseTreeParam: Twp, introNodes: Array[(Tree,Boolean)]): Array[Twp] =
    introNodes.map(introNode =>
      phraseTreeParam.copy(tree = introNode._1, phraseTree = if( introNode._2) introNode._1.parent else phraseTreeParam.tree ))

  def getIntroNode(tree: Tree): Array[(Tree,Boolean) ] = Array(
    //todo 连词
    if( tree.isPreTerminal && AdjTag.set.contains(tree.wordTag) ){
      (tree, false)
    }else if( tree.isPhrase){
      (tree.getLastWord, false)
    }else throw new RuntimeException("invalid tree"))

  def verifyIntroTag(introNode: Tree) =
    AdjTag.set.contains(introNode.wordTag)

  def makeIntroSense(twps: Array[Twp]): Array[Adj] = {
    val res = for( twp <- twps) yield {
      if (!verifyIntroTag(twp.tree)) logger.warn("Last node of the adj phrase is not an adj! tree: " + twp.tree)
      new PredicativeAdjFactory(twp).adj
    }
    res
  }

  /*
      @Override
      public void createOther(Twp phraseTwp, ThingFT introFT) {

      }*/
}