/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredicateFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.fromTree

import com.zpy.yearn.common.YearnException
import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.conj.Conj
import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred, V2args}
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap
import com.zpy.yearn.dict.meta.other.{DictUtil, Sense, SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.factory._
import com.zpy.yearn.pennTree.{SyntacPhrase, WordTag}
import com.zpy.yearn.structure.pos.{AdjTag, AdvP, Constituent}
import com.zpy.yearn.structure.yearnTree.Tree

import scala.collection.mutable.ArrayBuffer

/**
  * Created by zpy on 2019/3/5.
  */
trait PredicateFT extends SenseFT {
  val meanings: Things

  val senseClass: Class[_ <: Sense ]=
  {
    try {
      SenseClassMap.map((wordStr, classOf[V2args]))
    } catch {
      case _: NoSuchElementException =>
        try {
          SenseClassMap.map((wordStr, classOf[LinkV]))
        }catch {
          case _: NoSuchElementException =>
            try {
              SenseClassMap.map((wordStr, classOf[Adj]))
            }catch {
              case _: NoSuchElementException =>
                SenseClassMap.map((wordStr, classOf[VI]))
              case exception: Exception =>
                throw exception
            }
        }
      case exception: Exception =>
        throw exception
    }
  }
  val isAction: Boolean =
    wordStr match {
      case "打"| "想" => true //这两个词不能在PredicateFT中获取senseClass，所以先这样
      case _ =>classOf[TAction].isAssignableFrom(senseClass)
    }

  val sbjTwpOp: Option[Twp] = { //VP第一个兄弟节点
    val sbjPAhead = twp.phraseTree.syncSiblingsAhead(Set(SyntacPhrase.NP, SyntacPhrase.VP, SyntacPhrase.IP ))
    val sbjOp: Option[Tree] = if( sbjPAhead.nonEmpty && sbjPAhead.head.syncTag == SyntacPhrase.IP ){
      if( sbjPAhead.head.children.length > 1 ) throw new RuntimeException(s"More than 1 children under IP - ${sbjPAhead.head} !")
      else Some(sbjPAhead.head.lastChild)
    }else  {
      sbjPAhead.headOption
    }
    for(sbj <- sbjOp ) yield {
      twp.copy(tree = sbj,
        phraseConstituent = Constituent.SUBJECT,
        phraseType = sbj.syncTag
        , isMainStcVerb = false
      )
    }
  }
  val rawSbjs: Option[Things] = createNonePredPhraseNoPredicatives(sbjTwpOp)

  val sbjs: Things = {
    twp.rawSbjs = rawSbjs
    val res: Things = rawSbjs.getOrElse(
      twp.phraseConstituent match {
        case Constituent.PREDICATE =>
          twp.sentence.defaultSbjs.getOrElse(throw new RuntimeException("no sbj and default sbj!"))
        case _ =>
          if( twp.sentence.mainSbjs.isDefined) twp.sentence.mainSbjs.get
          else Array(
            if( isAction ){
              Ib()
            } else{
              Thing().some()
            }
          )
      }
    )
    if( twp.isMainStcVerb )
      twp.sentence.mainSbjs = Some(res)
    res
  }


  val adverbials: Set[Advb]

  //前面的兄弟advp解析出的Appendices
  val aheadSibAppendicesOp: Option[Appendices] = PredicateFT.advpSibsAhead(twp.phraseTree, twp, beforePhrase = true, NotDeclared())

  //前面的兄弟advp解析出的advb
  val aheadSibAdvbs: Set[Advb] =
    if (aheadSibAppendicesOp.isEmpty) Set()
    else aheadSibAppendicesOp.get.adverbials

  val conjFs: Seq[TPred => TPred] = {
    if (aheadSibAppendicesOp.isEmpty) Seq()
    else aheadSibAppendicesOp.get.conjs
  }

}

object PredicateFT {

  /**
    * senseTree的前面的兄弟AdvP
    *
    * @param senseTree
    * @param twp
    * @param beforePhrase
    * @return
    */
  def advpSibsAhead(senseTree: Tree, twp: Twp, beforePhrase: Boolean, central: TThing): Option[Appendices] = {
    val res: Seq[Appendices] = senseTree.siblingsAhead.filter((aheadSib: Tree) => {
      aheadSib.isPhrase && AdvP.set.contains(aheadSib.syncTag)
    }).map((advpTree: Tree) => {
      val advpTwp = twp.copy(tree = advpTree, phraseTree = advpTree, phraseConstituent = Constituent.ADVERBIAL, phraseType = advpTree.syncTag, isMainStcVerb = false)
      val appendices: Appendices = PredicateFT.parseAdvPhraseChildren(advpTree.children, advpTwp, beforePhrase, central)
      new Appendices {
        override val adverbials: Set[Advb] = appendices.adverbials
        override val complements: Seq[TPred => TPred] = Seq()
        override val noneAdvbPreps: Seq[NoneAdvbPrep] = appendices.noneAdvbPreps
        override val conjs: Seq[TPred => Conj] = appendices.conjs
      }
    })
    if (res.isEmpty) None
    else
      Some(res.reduceLeft((appendices1, appendices2) =>
        appendices1.add(appendices2)))
  }

  /**
    *
    * @param children
    * @param advpTwp
    * @param beforePhrase 是否修饰主语。比如在句首的“只有”（例如：只有他在），其实是修饰主语的。
    * @throws
    * @return
    */
  @throws[YearnException]
  def parseAdvPhraseChildren(children: Array[Tree], advpTwp: Twp, beforePhrase: Boolean, central: TThing): Appendices
  = {
    var advbs: Set[Advb] = Set()
    val noneAdvbPrepRs: ArrayBuffer[NoneAdvbPrep] = ArrayBuffer()
    val conjFs: ArrayBuffer[TPred => Conj] = ArrayBuffer()
    for (advpKid <- children) {
      if (advpKid.isPreTerminal) {
        val advpKidTwp = advpTwp.copy(tree = advpKid, isMainStcVerb = false)
        if (NoneAdvbPrepFactory.words.contains(advpKidTwp.tree.word)) {
          noneAdvbPrepRs ++= DictUtil.createNoneAdvbPrepSense(advpKidTwp)
        } else if (ConjFactory.words.contains(advpKidTwp.tree.word)) {
          conjFs += new ConjFactory(advpKidTwp).conjF
        } else if (advpKidTwp.tree.wordTag == WordTag.P) {
          advbs ++= new PrepFactory(advpKidTwp).preps //介词，可能多个宾语，故可能多个结果
        }else if( advpKid.tag == WordTag.LC ){
          val locationTree = advpKid.siblingsAhead.last //LC（“中”）前面的表示位置的短语
          new LocalizerModifierFactory(advpTwp.copy(tree = locationTree, phraseTree = locationTree, isMainStcVerb = false)).meanings.foreach( advbs += _)
        } else
          new AdverbialAdvFactory(advpKidTwp, beforePhrase, central).advb.map(advbs += _) //副词
      }
      else {
        val advTrees = advpKid.filterKidsByWordTags(AdjTag.set)
        if( advTrees.nonEmpty ){
          advTrees.foreach( advTree => {
            val advpKidTwp = advpTwp.copy(tree = advTree, isMainStcVerb = false)
            new AdverbialAdvFactory(advpKidTwp, beforePhrase, central).advb.map(advbs += _) //副词
          })
        }
        //介词短语宾语，在介词中去创建了
        //createPhrase(Some(twp))
      }
    }
    new Appendices {
      override val adverbials: Set[Advb] = advbs
      override val complements: Seq[TPred => TPred] = Seq()
      override val noneAdvbPreps: Seq[NoneAdvbPrep] = noneAdvbPrepRs
      override val conjs: Seq[TPred => Conj] = conjFs
    }
  }
}
