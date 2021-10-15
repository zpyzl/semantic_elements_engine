/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdjFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

import com.zpy.yearn.dict.basic.ib.Good
import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.adv.prep.NoneAdvbPrep
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.fromTree.{Appendices, PredicateFT}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.ib.{Brave, Miserable}
import com.zpy.yearn.dict.modifier.adj.mood.{Dejected, Satisfied}
import com.zpy.yearn.dict.modifier.adj.thing.{Basic, Complete, Extreme, Useless}
import com.zpy.yearn.dict.prep.thing.In
import com.zpy.yearn.dict.pronoun.question.HowAbout
import com.zpy.yearn.pennTree.SyntacPhrase
import com.zpy.yearn.structure.yearnTree.Tree

/**
  * Created by zpy on 2019/3/31.
  */
abstract class AdjFT(override val twp: Twp) extends SenseFT(twp) with ModifierFT  {


  //找到形容词前面的一个副词短语。
  override val central: TThing = NotDeclared()

  val appendices: Option[Appendices ] = getAppendices

  val adverbials: Set[Advb] = {
    if( appendices.nonEmpty) {
      appendices.get.adverbials
    }else Set()
  }
  val setOfIn: Set[In] = adverbials.filter(_.isInstanceOf[In]).map(_.asInstanceOf[In])

  private def getAppendices: Option[Appendices] = {
    val closestAppendices: Option[Appendices] = PredicateFT.advpSibsAhead(twp.tree,twp, beforePhrase = false, central )
    if( closestAppendices.isDefined){
      Some(closestAppendices.get.add( parseParentSibs(twp.tree)))
    }else parseParentSibs(twp.tree)
  }

  private def parseParentSibs(tree: Tree): Option[Appendices ] = {
    if( tree.parent.tag != SyntacPhrase.NP && tree.parent.tag != SyntacPhrase.IP ) {
      val parentAdvpSibs: Option[Appendices] = PredicateFT.advpSibsAhead(tree.parent, twp, beforePhrase = false, central)
      if( parentAdvpSibs.isDefined ) {
          Some(parentAdvpSibs.get.add( parseParentSibs(tree.parent)))
      }else parseParentSibs(tree.parent )
    }else None
  }

  val noneAdvbPreps: Seq[NoneAdvbPrep] = if( appendices.isDefined) appendices.get.noneAdvbPreps else Seq()

  val rawAdj: Adj = wordStr match {
    case "基本" =>
      Basic(setOfIn.head)
    case "极端" => new Extreme()
    case "完整" => Complete()
    case "一无是处"|"没用" => new Useless()
    case "沮丧" => Dejected()
    case "怎么样" => HowAbout()
    case "满意" => Satisfied()
    case "勇敢" => Brave()
    case "痛苦" => Miserable()
    case "好" => Good()
    //case "坏" => new Bad()
  }

  val adj: Adj = {
    rawAdj.twp_=( Some(twp))
    rawAdj.mods_=( rawAdj.mods ++ adverbials)
  }

}
