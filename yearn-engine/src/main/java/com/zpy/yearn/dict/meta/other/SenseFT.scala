/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:SenseFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.other

import com.zpy.yearn.dict.meta.hasArgs.LinkV
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.pennTree.{SyntacPhrase, Tag, WordTag}
import com.zpy.yearn.structure.factory._
import com.zpy.yearn.structure.pos.{AdjTag, Constituent, PredicateVerbs}
import com.zpy.yearn.structure.yearnTree.Tree

import scala.collection.mutable

/**
  * 句子里出现的表示某个确定义项的单词
  *
  * Created by zpy on 2018/7/23.
  */
abstract class SenseFT(val twp: Twp) extends HasCommonType {
  /*
    protected def toSenseF(temp: Object): SenseF = {
      temp match {
        case sense: Sense => _ => sense
        case senseF: SenseF => senseF
      }
    }*/

  def filterAdvb[T](senses: Seq[Sense], clazz: Class[T]): Option[T] =
    senses.filter(sense => clazz.isAssignableFrom(sense.getClass)).map(_.asInstanceOf[T]).headOption


  def sib1TreeOp(tree: Tree): Option[Tree] = {
    val sibsOp: Option[mutable.Buffer[Tree]] = Option(tree.siblings)
    if (sibsOp.isDefined) {
      sibsOp.get.headOption
    } else None
  }

  def sibTreeBehind(tagSet: Set[Tag]): Seq[Tree] = {
    twp.tree.siblingsBehind(tagSet)
  }

  def followingSib1TreeOp(tagSet: Set[Tag]): Option[Tree] = {
    twp.tree.followingSibling(tagSet)
  }

  def followingSib1TreeOp: Option[Tree] = {
    twp.tree.followingSibling
  }

  val wordStr: String = twp.tree.word

  val tag: String = twp.tree.tagStr

  //val collinsPOS: CollinsPOS

  def createNonePredPhraseNoPredicatives(twp: Option[Twp]): Option[Things] = {
    createNonePredPhrase(twp) match {
      case Left( thingsOp ) => thingsOp
      case Right(_) => None
    }
  }
  def createNonePredPhrase(twp: Option[Twp]): Either[Option[Things], Array[Predicative]] = {
    if ((twp.isDefined && AdjTag.set.contains(twp.get.tree.getLastWord.wordTag) && twp.get.phraseType != SyntacPhrase.IP) //最后一个词是形容词，说明是补语，不在这里解析
      &&
      !(LinkV.words.contains(wordStr) && PredicateVerbs.tags.contains(WordTag.valueOf(tag))) //谓语动词是系动词，说明是表语不是补语，所以要排除
    ) {
      Left(None)
    }/* else if (twp.isDefined && twp.get.tree.tag == SyntacPhrase.IP &&
      twp.get.tree.children.length == 1) {
      val phraseTree = twp.get.tree.getChild(0)
      createNonePredPhrase(Some(twp.get.copy(
        tree = phraseTree,
        phraseType = SyntacPhrase.valueOf(phraseTree.tag.toString)
      )))
    }*/
    else if (twp.isDefined && twp.get.phraseType == SyntacPhrase.IP) {
      //从句
      val vps = twp.get.tree.filterKidsSync(Set(SyntacPhrase.VP))
      if (vps.length > 1) throw new RuntimeException(s"more than 1 VP under IP ${twp.get.tree}!")
      val vpTree = vps.head
      val res: Things = MainPredicateFactory.createPred(twp.get.copy(
        tree = vpTree, phraseTree = vpTree, phraseType = SyntacPhrase.VP
      )).map(_.asInstanceOf[TThing])

      if ((twp.get.tree.filterKidsSync(Set(SyntacPhrase.NP)).length == 0) &&
        LinkV.words.contains(wordStr)) {
        //如果IP下面没有NP，认为没有主语，并且谓语是系动词，认为是系动词的表语，只取生成句子的的宾语出来
        Right(res.map(_.asInstanceOf[LinkV].predicative))
      } else Left(Some(res))
    }
    else if(twp.isDefined && twp.get.tree.getLastWord.wordTag == WordTag.DEC &&
      AdjTag.set.contains(twp.get.tree.taggedWord(twp.get.tree.leaves.size - 2 ).wordTag )){
      val tp = twp.get
      //以“形容词+的”结尾，说明是形容词短语。因为其可能错误地被NP包裹，所以要提取出来这种case
      Right(AdjPFactory.create(tp.copy(tree = tp.tree.taggedWord(tp.tree.leaves.size - 2 ))).map(_.asInstanceOf[Predicative]))
    }
    else
      Left(
        for (tp <- twp) yield {
          val factory: ThingFactory =
            tp.phraseType match {
              case SyntacPhrase.NP => NPFactory
              case SyntacPhrase.VP if tp.tree.recursiveContains(PredicateVerbs.tags + WordTag.VA) => VPFactory
              case _ if tp.phraseConstituent.equals(Constituent.OBJ) || tp.phraseConstituent.equals(Constituent.SUBJECT) => NPFactory
              //todo 作为宾语的其他短语
            }
          factory.create(tp)
        })
  }

  def sibBehindTwpOp(phraseConstituent: Constituent, tagSet: Set[Tag]): Option[Twp] = {
    val sibsBehind = sibTreeBehind(tagSet)
    if (sibsBehind.isEmpty) None
    else {
      val sibTree = sibsBehind.head
      val phraseType =
        if( sibTree.isPhrase) sibTree.syncTag
        else if( PredicateVerbs.tags.contains(sibTree.wordTag))//后面不是跟短语，
          SyntacPhrase.VP
        else throw new RuntimeException("sibling behind is neither a phrase nor a verb!")
      Some(twp.copy(tree = sibTree,
        phraseConstituent = phraseConstituent,
        phraseType = phraseType,
        isMainStcVerb = false
      ))
    }
  }

  def followingSib1TwpOp(phraseConstituent: Constituent): Option[Twp] =
    for {
      sib1Tree <- followingSib1TreeOp
    } yield
      twp.copy(tree = sib1Tree,
        phraseConstituent = phraseConstituent,
        phraseType = sib1Tree.syncTag
        , isMainStcVerb = false
      )

/*

  def followerOp(phraseConstituent: Constituent): Option[Things] =
    createNonePredPhraseNoPredicatives(followingSib1TwpOp(phraseConstituent))
*/

  def senseOfSibBehindOp(phraseConstituent: Constituent, tagSet: Set[Tag]): Either[Option[Things], Array[Predicative]] =
    createNonePredPhrase(sibBehindTwpOp(phraseConstituent, tagSet))


}