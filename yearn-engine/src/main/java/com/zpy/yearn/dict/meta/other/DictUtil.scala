/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DictUtil.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.noun.NounFT
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap
import com.zpy.yearn.dict.meta.predicate.fromTree.PredicateFT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.factory._
import com.zpy.yearn.pennTree.WordTag
import com.zpy.yearn.structure.pos.{AdjTag, Constituent, PredicateVerbs}

/**
  * Created by zpy on 2018/12/7.
  */
object DictUtil extends HasCommonType {
  @throws[YearnException]
  def createModifierSenseObject(twp: Twp, central: TThing): Unit = {
    val word = twp.tree.word
    val tag = twp.tree.wordTag
    tag match {
      case WordTag.CD => new NumeralFactory(twp,central)//数词
      case WordTag.DT => new DeterminerFactory(twp,central)
      //case WordTag.P => new PrepFactory(twp)
      case _ if AdjTag.set.contains(tag) => new ModifierAdjFactory(twp, central)//定语

      case _ =>
        word match {
          case _ =>
        }
    }

  }

  @throws[YearnException]
  def createNoneAdvbPrepSense(twp: Twp): Seq[ NoneAdvbPrep] = {
    twp.tree.wordTag match {
      case WordTag.P => new NoneAdvbPrepFactory(twp).noneAdvbfs//介词，可能多个宾语，故可能多个结果
    }
  }

  @throws[YearnException]
  def createPred(twp: Twp): PredicateFT = {
    val word: String = twp.tree.word
    val tag: WordTag = twp.tree.wordTag
    tag match {
      case WordTag.VA => new PredicateAdjFactory(twp)
      case _ if PredicateVerbs.tags.contains(tag) => new VerbFactory(twp) //因为其属性——主语宾语，可能有多个结果
      //case _ if WayVerbStr.contains(word) => new WayVerbFactory(twp)
      //case _ if AuxVerbFactory.words.contains(word) => new AuxVerbFactory(twp) //多

      //case "指" =>
      //  new MeanFT(twp)
      case _ =>
        twp.phraseConstituent match {
          //非谓语动词作谓语，暂认为是省略系动词的系表结构
          case Constituent.PREDICATE if !PredicateVerbs.tags.contains(tag) => new PredicateAdjFactory(twp )
          case _ => throw new RuntimeException("not in dict! Please add verb strings in VSts. " + word)
        }
    }
  }
  @throws[YearnException]
  def createNoun(twp: Twp): NounFT = {
    val word: String = twp.tree.word
    val tag: WordTag = twp.tree.wordTag
    val nounFT: NounFT = tag match {
      case WordTag.PN => new PronounFactory(twp)//单结果
      case _ =>
        word match {
          case _ => new NounFactory(twp)//名词，定语不会产生多个结果，单结果
          //case _ if ConjFactory.words.contains(word) => new ConjFactory(twp)
        }
    }
    nounFT
  }
/*
  def createPred(twp: Twp): Verb = {
    val leaf = TreeF.getNecessaryLeaf(twp.tree)

    //非谓语动词作谓语，暂认为是省略系动词的系表结构
    if (!PredicateVerbs.tags().contains(leaf.getWordTag)) new BeFactory(twp).predicate
    else throw new RuntimeException("not in dict! " + leaf.getWord)
  }*/

}
