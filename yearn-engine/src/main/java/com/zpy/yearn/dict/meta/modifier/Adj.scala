/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Adj.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

import com.zpy.yearn.dict.basic.logic.{Be, More}
import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.{HasElements, HasNamedElements, TPred, V2args, V3args, V4args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2018/12/12.
  */
trait Adj extends Modifier with Predicative {


  //val argInfConfirm: Set[Pred] = argInf1ArgFunc(sbj)

  // def inf( degree: Degree): Option[Pred ] = None
  def eqvlByAdv_=(adv: AdjAdv): Option[TPred] = None

  //if sth is adj, then adj contains sth
  def contains(that: TThing): Boolean = false

  def explain(pred: TPred, centralExplainer: Explainer): Option[TThing] = {
    selfMeaning(pred, centralExplainer, adjAdvMeaning(pred, this, centralExplainer))
  }

  def adjAdvMeaning(pred: TPred, adj: Adj, centralNoun: Explainer): Set[AdjAdv] = {
    /*    if( mods.size > 1)
          throw new RuntimeException(s"The adj - '$adj''s mods size is more than 1! "*/
    mods.map {
      case adjAdv: AdjAdv => adjAdv.explainAdjAdv(pred)
      case _ => None
    }.filter(_.isDefined).map(_.get)
  }

  /**
    * 形容词的解释只需要实现此方法。如果有解释就要返回Some
    *
    * @param centralExplainer
    * @return
    */
  def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = Left(None)

  def selfMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Option[TThing] = {
    addAdvsToAdj(adjMeaning(pred, centralExplainer, advs), advs) match {
      case Left(thingOp) =>
        thingOp.map( thing =>
          centralExplainer.explainForModExplain(Set(thing), None,this))
      case Right(adj) =>
        Some( centralExplainer.explainForModExplain(Set(adj), None,this))
    }
  }

  //把解释后的adv加在adj的解释里。比如most basic=>basic( more than..)
  // =>    Left(Some(Cause((central), Part(Set(**Many()**)).otherThan(central).of(scope) )) ) ) 替换其中的Many为more than(most的解释)
  private def addAdvsToAdj(adjMeaning: Either[Option[TThing], Adj], advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    adjMeaning match {
      case Left(thingOp)=>
        if( thingOp.isDefined ) {
          Left(Some({
            thingOp.get match {
              case v4args: V4args =>
                checkMods(v4args.sbj, advs)
                checkMods(v4args.obj, advs)
                checkMods(v4args.arg3, advs)
                checkMods(v4args.arg4, advs)
              case v3args: V3args =>
                checkMods(v3args.sbj, advs)
                checkMods(v3args.obj, advs)
                checkMods(v3args.arg3, advs)
              case v2args: V2args =>
                checkMods(v2args.sbj, advs)
                checkMods(v2args.obj, advs)
              case pred: TPred =>
                checkMods(pred.sbj, advs)
            }
            thingOp.get
          }))
        }else Left(None )
      case Right(adj) => Right(adj)
    }
  }

  /**
    * 这个方法最开始创建的目的是什么？似乎是把副词中的More转移到参数上，但为什么这样呢？例子？
    * @param thing
    * @param advs
    * @return
    */
  private def checkMods(thing: TThing, advs: Set[AdjAdv]): TThing = {
    thing.mods.foreach {
      case quantifier: TQuantifier if advs.exists(_.isInstanceOf[More]) =>
        thing.mods_=(
          thing.mods - quantifier + advs.filter(_.isInstanceOf[More]).head)
      case _ =>
    }

    thing
  }

  /**
    * 在be作谓语的句子中的含义
    *
    * @param be
    * @param sbjExplainer
    */
  def beMeaning(be: Be, sbjExplainer: Explainer, pred: TPred): Boolean =
    adjMeaningWithAdv(be, sbjExplainer) match {
      case Left(thingOp) =>
        thingOp.foreach( th => {
          //be作谓语，不是定语从句，需要替换其中的所有Central
          th match {
            case hasNamedElements: HasNamedElements =>
              hasNamedElements.replaceCentral(sbjExplainer.fm )
            case _ =>
          }
          be.nature_+=(th, pred)
        })
        thingOp.nonEmpty
      case Right(adj) =>
        be.nature_+=(Be(sbjExplainer.fm, adj), pred)
        true
    }


  /* override def copyTo(that: Thing): Unit = {
     that.m_+=( this.newInstance(that).asInstanceOf[Sense ])
   }*/
  /*
    def getCentral(): Thing = Thing().some()
    def setCentral(thing: Thing): Unit*/

  private def adjMeaningWithAdv(pred: TPred, centralExplainer: Explainer): Either[Option[TThing], Adj] = {
    adjMeaning(pred, centralExplainer, adjAdvMeaning(pred, this, centralExplainer))
  }

  override def equals(obj: Any): Boolean = super.equals(obj)

  override def copyAddMods(mods: Set[Sense]): Adj.this.type = {
    val cl = super.copyAddMods(mods)
    cl.to = this.to
    cl
  }

  override def copyReplaceMods(mods: Set[Sense]): Adj.this.type = {
    val cl = super.copyReplaceMods(mods)
    cl.to = this.to
    cl
  }
  // override def toString: String = className //+ " " + sbj.toString

}
