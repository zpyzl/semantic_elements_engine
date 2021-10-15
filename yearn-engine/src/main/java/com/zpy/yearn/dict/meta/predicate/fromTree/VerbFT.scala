/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VerbFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.fromTree

import com.zpy.yearn.dict.adv.Not
import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred, V2args}
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.senseClassMap.SenseClassMap
import com.zpy.yearn.dict.meta.other.{Predicative, Sense, Twp}
import com.zpy.yearn.dict.meta.predicate.auxVerb.AuxVerb
import com.zpy.yearn.dict.prep.whom.{To, With}
import com.zpy.yearn.factory.{AuxVerbFactory, ComplementAdjFactory}
import com.zpy.yearn.pennTree.{SyntacPhrase, WordTag}
import com.zpy.yearn.structure.factory.{NPFactory, VPFactory}
import com.zpy.yearn.structure.pos._
import com.zpy.yearn.structure.tense.Tense
import com.zpy.yearn.structure.tense.Tense.Tense
import com.zpy.yearn.structure.yearnTree.Tree

import scala.collection.mutable.ArrayBuffer

/**
  * 动词
  * Created by zpy on 2018/11/9.
  */
trait VerbFT extends PredicateFT {

  //val verb: Verb
  var denied: Boolean = false
  var tense: Tense = Tense.SimplePresent

  //宾语是HasObj的，句子可能没有宾语
  //val adverbial: Option[Adverbial] //todo 状语其实是thing的properties

  var objOrPredicatives: Either[Option[Things], Array[Predicative]] = senseOfSibBehindOp(Constituent.OBJ, ObjTag.set)

  //twp.sentence.sbjs = sbjFs
  //todo 动词没有主语的情况：1.祈使句。2.一般情况。如“吸烟有害”。这两种都可以看做省略主语


  val appendices: Appendices = parseVPChildren(twp.phraseTree.children, twp)
  val adverbials: Set[Advb] = {
    val advbs: Set[Advb] = appendices.adverbials ++ aheadSibAdvbs
    val advbsSimplified: Set[Advb ] = advbs.count(_.isInstanceOf[Not]) % 2 match {
      case 0 => advbs.filterNot(_.isInstanceOf[Not])
      case 1 => Set(Not()) ++ advbs.filterNot(_.isInstanceOf[Not])
    }

    val auxVerb: Option[AuxVerb] = {
      val auxVerbTreeOp = twp.phraseTree.filterKidsByWord(PredicateVerbs.tags, AuxVerbFactory.words)
      if( auxVerbTreeOp.isDefined) {
        //todo auxVerbTreeOp 需要在动词之前
        //不能和当前动词相同
        if (auxVerbTreeOp.get.word == wordStr) {
          None
        }else{
          Some(new AuxVerbFactory(twp.copy(tree = auxVerbTreeOp.get, isMainStcVerb = false )).auxVerb)//todo 暂时只支持情态动词后跟一个动词
        }
      }else None
    }

    if( auxVerb.isDefined){
       advbsSimplified + auxVerb.get
    }else advbsSimplified
  }

  val noneAdvbPreps: Seq[NoneAdvbPrep] = appendices.noneAdvbPreps

  def withWhom(): Option[With] = filterAdvb[With]( noneAdvbPreps, classOf[With])

  def toWhom: Option[To] = filterAdvb[To]( noneAdvbPreps, classOf[To])

  //补语，complementAdjFactory(verb),补充动词的什么? 好：形容事物的，补充动词的结果；会：形容人的，是主语（人）的补充，
  val complements: Seq[TPred =>TPred] = appendices.complements

  var passiveVoice: Boolean = false

  def parseVPChildren(vpKidTrees: Array[Tree], twp: Twp): Appendices = {
    var advbs: Set[Advb] = Set()
    var compls: ArrayBuffer[TPred => TPred] = ArrayBuffer()
    var noneAdvbPs: ArrayBuffer[NoneAdvbPrep] = ArrayBuffer()

    def iterateVpKidTrees(vpKidTrees: Array[Tree]): Unit = {
      for {vpKidTree <- vpKidTrees} {
        if (vpKidTree.isPhrase) {
          val vpKidSyncTag = vpKidTree.syncTag

          if (AdvP.set.contains(vpKidSyncTag) ) { //是ADVP，则继续遍历儿子
            val advpTwp = twp.copy(tree = vpKidTree, phraseTree = vpKidTree, phraseConstituent = Constituent.ADVERBIAL, phraseType = vpKidSyncTag, isMainStcVerb = false)
            val appendices = PredicateFT.parseAdvPhraseChildren(vpKidTree.children, advpTwp, beforePhrase = false, NotDeclared())
            advbs ++= appendices.adverbials
            noneAdvbPs ++= appendices.noneAdvbPreps
          }
          else if (VP.set.contains(vpKidSyncTag)) {
            //VP下面的VP，如果以形容词结尾，是补语
            if (AdjTag.set.contains(vpKidTree.getLastWord.wordTag)) {
              //动词后的形容词：补语
              compls += new ComplementAdjFactory(
                twp.copy(tree = vpKidTree.getLastWord, phraseTree = vpKidTree, phraseConstituent = Constituent.COMPLEMENT, phraseType = SyntacPhrase.VP, isMainStcVerb = false)).sense
            }
          }
          else if (vpKidSyncTag == SyntacPhrase.IP) {
              //如果是NP+IP，则NP是IP表示的宾语从句的主语
              for (aheadSib <- vpKidTree.siblingAhead) {
                if (aheadSib.isPhrase && aheadSib.syncTag == SyntacPhrase.NP) {
                  twp.sentence.defaultSbjs = Some(NPFactory.createSbj(aheadSib, twp))
                  twp.sentence.mainSbjs = Some(sbjs)
                  twp.isMainStcVerb = false
                  objOrPredicatives = Left( Some(
                    if (vpKidTree.children.length == 1 && vpKidTree.getChild(0).syncTag == SyntacPhrase.VP) {
                      VPFactory.createPred(vpKidTree.getChild(0), twp)
                    } else throw new RuntimeException("NP+IP应该是宾语从句，但IP的子节点不是唯一一个VP")
                  ))
                }else if( aheadSib.isPreTerminal && PredicateVerbs.tags.contains(aheadSib.wordTag )){
                  //VP下的IP，如果IP前面就是动词，则是宾语从句；

                }
              }

          }

          else {
            //补语

          }
        }else if( vpKidTree.wordTag == WordTag.SB ){//被
          //把主语变为宾语
          passiveVoice = true
        }
        else None
      }
    }

    if( !(vpKidTrees.length == 1 && !vpKidTrees.head.isPhrase))//排除不是短语的情况
      iterateVpKidTrees(vpKidTrees)

    new Appendices {
      override val adverbials: Set[Advb] = advbs
      override val complements: Seq[TPred => TPred] = compls
      override val noneAdvbPreps: Seq[ NoneAdvbPrep] = noneAdvbPs
    }
  }



  override def toString: String = {
    sbjs + " " + super.toString
  }

}


