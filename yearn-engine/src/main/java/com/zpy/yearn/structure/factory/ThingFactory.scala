/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ThingFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.other.{HasCommonType, Twp}
import com.zpy.yearn.structure.yearnTree.Tree
import org.slf4j.LoggerFactory

/**
  * Created by zpy on 2018/11/22.
  */
trait ThingFactory extends HasCommonType{
  private val logger = LoggerFactory.getLogger(classOf[ThingFactory])

  @throws[YearnException]
  def create(phraseTreeParam: Twp): Things = {
    logger.debug(s"phraseTreeParam: \n $phraseTreeParam")
    val introNodes: Array[(Tree,Boolean)] = getIntroNode(phraseTreeParam.tree)
    //intro和短语的predicate、成分、短语类型一样， intro的intro是短语的引导词
    val introTwps = getIntroTwp(phraseTreeParam,introNodes)

    //状语函数在谓语sense创建后应用 ~~状语中的介宾短语需要先创建好，以传给谓语做宾语参数，所以先createOther
    //val otherTwp = createOtherTwp(phraseTreeParam, introFT.meaning)
    //createOther(phraseTreeParam)
     makeIntroSense(introTwps)
  }
  def getIntroTwp(phraseTreeParam: Twp, introNodes: Array[(Tree,Boolean)]): Array[Twp] =
    introNodes.map(introNode =>
      phraseTreeParam.copy(tree = introNode._1, phraseTree = if( introNode._2) introNode._1.parent else phraseTreeParam.tree ))/*
      这里introNode._2为true表示两个动词并列在VP下时（见下），这时phraseTree应该为(VC 是)的上层VP，而非一般情况下最外层的VP
   (IP
    (VP
      (VP (VV 克服)
        (NP (NN 困难)))
      (VP (VC 是)
        (NP
          (CP
            (IP
              (VP (VA 痛苦)))
            (DEC 的)))))))
      */

  /**
    *
    * @param tree
    * @throws
    * @return 中心语词、中心语所在短语组成的元组
    */
  @throws[YearnException]
  def getIntroNode(tree: Tree): Array[(Tree,Boolean)]

  def verifyIntroTag(introNode: Tree): Boolean

  @throws[YearnException]
  def makeIntroSense(twps: Array[Twp]): Things

  //void setPhraseTypeOfIntro(TreeWordParam introTwp);
  //@throws[YearnException]
  /*def createOtherTwp(phraseTwp: Twp, intro: Thing): Twp = {
    phraseTwp.copy(introducer = Some(intro))
  }*/
  //def createOther(phraseTwp: Twp, introFT: ThingFT): Unit
}