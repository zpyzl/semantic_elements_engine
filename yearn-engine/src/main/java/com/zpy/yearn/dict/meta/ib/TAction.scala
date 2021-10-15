/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Action.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.ib

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.TCentral
import com.zpy.yearn.dict.meta.none.notDeclared.PredNotDeclared
import com.zpy.yearn.dict.meta.predicate.linkOrNot.Verb
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.verb.auxVerb.Want
import com.zpy.yearn.structure.pos.Constituent

import scala.collection.mutable

/**
  * IB的动作
  * Created by zpy on 2018/11/9.
  */
trait TAction extends Verb  {
  val actor: TIb
  override val sbj = actor

  setActorConstituent

  protected def setActorConstituent = {
    if (actor == null)
      throw new RuntimeException("actor is null!")
    else {
      constituentTable += (actor.seq -> Constituent.SUBJECT)
      setConstituentForArgOfArg(actor, Constituent.SUBJECT)

    }
  }

  sbj.phraseConstituent_=( Constituent.SUBJECT)
  sbj.predTakingThis = Some(this )
  sbj.from = (SourceType.AS_SBJ, Set(this))

  //val word: Action = DoSomeAction()

   //val argInfConfirm: Set[Pred] = Set()

  //use是way在Action中的形式
  val uses: mutable.Set[TThing] = mutable.Set[TThing]()

  def use_+=(useThing: TThing): this.type = {
   // super.way_=(useThing)
    uses += useThing
    this
  }

  var wants: mutable.Set[Want] = mutable.LinkedHashSet() //人的行为是由欲望决定
  //主语IB的欲望
  //Desire(sbj)//调用超类构造器

  //def this(desire: Desire)

  // override def causes[Desire]: mutable.Set[Desire] = desires
  private var targetData: TPred = PredNotDeclared()
  val result: TPred = PredNotDeclared()
  def target: TPred = targetData

  def target_=(target: TAction): this.type = {
    targetData = target
    super.causes_+=(new Want(target))
    this
  }

  override def copy(): TAction.this.type = {
    copyAttrs(super.copy())
  }

  override def copyAttrs(clone: TAction.this.type): TAction.this.type = {
    val clone2 = super[Verb].copyAttrs(clone)
    clone2.wants = this.wants
    clone2.targetData = this.targetData
    clone2
  }

  override def equals(obj: Any): Boolean = obj match {
    case actionObj: TAction =>
      val b1 = super.equals(obj)
      val b2 = if(actor.isInstanceOf[TCentral]) true else this.actor.equals(actionObj.actor)
      b1 && b2
    case _ => false
  }


  override def toString: String =
    super.toString + actionStr


  def actionStr: String =
    (if(uses.nonEmpty) ", using " + ("" /: uses)(_ + _) else "")
  // (if(wants.nonEmpty) ", to " + ("" /: wants)(_ + _) )

  def inOrderTo(aim: TAction ): this.type = {
    copyAddMods().target_=( aim )
  }
}