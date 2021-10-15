/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VPFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.factory

import com.typesafe.scalalogging.Logger
import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.other.{DictUtil, Twp}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.pennTree.SyntacPhrase
import com.zpy.yearn.structure.pos.Constituent
import com.zpy.yearn.structure.yearnTree.Tree


/**
  * Created by zpy on 2019/3/5.
  */
object VPFactory extends ThingFactory {
  val logger: Logger = Logger(this.getClass)

  override def getIntroNode(vpTree: Tree): Array[(Tree,Boolean)] = {
    /*
    todo 并列的动词，第一个动词的宾语在第二个动词的VP中
    (ROOT
  (IP
    (NP (PN 你))
    (VP
      (VP (VV 教育))
      (PU 、)
      (VP
        (VV 震撼)
        (AS 了)
        (NP (PN 我))))))
     */
    val ccs = vpTree.children.filter(_.isCC) //连词
    if (ccs.nonEmpty) {
      vpTree.children.filter(kid =>
        !kid.isCC && kid.syncTag == SyntacPhrase.VP)
        .map(vpT => {
          val introNodeRes = findPredicateVerb(vpT)
          (introNodeRes._1.getOrElse(vpT.getLastWord), introNodeRes._2)
        })
    }
    else Array({
      val introNodeRes = findPredicateVerb(vpTree)
      (introNodeRes._1.getOrElse(vpTree.getLastWord), introNodeRes._2)
    }) //如果找不到动词，先处理只有一个词的情况
  }

  override def verifyIntroTag(introNode: Tree): Boolean = true

  override def makeIntroSense(twps: Array[Twp]): Array[TThing] = {
    val temp: Array[Array[TThing]] = for (twp <- twps) yield DictUtil.createPred(twp).meanings
    temp.flatten
  }

  /**
    *
    * @param tree
    * @throws
    * @return 元组中_2表示是否用此tree的parent作为twp.phraseTree
    */
  @throws[YearnException]
  def findPredicateVerb(tree: Tree): (Option[Tree],Boolean) = {
    val directPredVerb: Tree = tree.filterPredicateInKid()
    if (directPredVerb != null) {
      (Some(directPredVerb),false)
    } else {
      val preds = tree.children
        .filter(kid => kid.isPhrase && kid.tag != SyntacPhrase.DVP )
        .map(phrase=> findPredicateVerb(phrase))
        .filter(_._1.isDefined)
      if (preds.isEmpty) {
        (None,false)
      } else if (preds.length == 1) {
        preds.head
      }else {
        preds(1).copy(_2 = true)//多个动词：谓语开头的祈使句先不考虑；那么第一个动词是主语，第二个是谓语，剩下的是宾语。
      }
    }
  }

  def createPred(vpTree: Tree, twp: Twp): Things = create(twp.copy(tree = vpTree, phraseTree = vpTree,phraseConstituent = Constituent.PREDICATE, phraseType = SyntacPhrase.VP, isMainStcVerb = false))
  // 当发现两个动词，要留一个。检查每个动词是否有其他词性（从第二个动词开始）。比如“做不会”，“会”检查是有形容词性，而且V+ADJ形成补语结构
}
