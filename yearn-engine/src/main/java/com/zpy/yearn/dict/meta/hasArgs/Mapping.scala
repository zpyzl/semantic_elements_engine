/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Cause.scala
 * Date:2020/1/22 上午11:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

import com.zpy.yearn.dict.conj.Conj
import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.Nothing

/**
  * Created by zpy on 2019/5/16.
  */
abstract class Mapping extends V4args with Conj {
  val reason1: TThing = NotDeclared()
  var reason2: Option[TThing] = None
  var reason3: Option[TThing] = None
  val result: TThing = NotDeclared()

  def setReasons(reasons: Seq[TThing], result: TThing): Unit ={
    if( reasons.size > 3 )
      throw new RuntimeException("Mapping which has > 3 reasons are not supported!")
    if( reasons.size > 1 ){
      reason2 = Some(reasons(1))
      if( reasons.size > 2 ){
        reason3 = Some( reasons.last)
      }
    }
  }

 def reasons: Seq[TThing] = {
   var r = Seq(reason1)
   if( reason2.isDefined) r :+= reason2.get
   if( reason3.isDefined) r :+= reason3.get
   r
 }

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = {
    var r = Map(ElementName.reason1 -> reason1)
    if( reason2.isDefined) r += ElementName.reason2 -> reason2.get
    if( reason3.isDefined) r += ElementName.reason3 -> reason3.get
    r += ElementName.result -> result
    r
  }

  protected def setFrom = {
    reasons.foreach(r => {
      r.from = (SourceType.AS_REASON, Set(this))
      r.predTakingThis = Some(this)
    })
    result.from = (SourceType.AS_RESULT, Set(this))
    result.predTakingThis = Some(this)
  }

  override val sbj: TThing = reasons.head
  override val obj: TThing = reasons.size match {
    case 1 => result
    case _ => reasons(1)
  }
  override val arg3: TThing =
    if( reasons.size >= 3 ) reasons(2)
    else if( reasons.size == 2 ) result
    else Nothing()
  override val arg4: TThing =
    if( reasons.size >= 4 ) reasons(3)
    else if( reasons.size == 3 ) result else Nothing()

  def newInstance(reasons: Seq[TThing], result: TThing ): this.type = newInstance(reasons, result, this.mods)

  def newInstance(reasons: Seq[TThing], result: TThing, mods: Set[Sense]): this.type = {
    try {
      this.getClass.getConstructors.filter(_.getParameterTypes.contains(classOf[Seq[_]])).head.newInstance(reasons, result).asInstanceOf[this.type].mods_=( mods)
    }catch {
      case exception: Exception =>
        print()
        throw exception
    }
  }
  def copyCauseAddMods(reasons: Seq[TThing]= this.reasons, result: TThing = this.result, addedMods: Set[Sense] = Set() ): this.type = copyCauseReplaceMods(reasons, result, this.mods ++ addedMods)

  def copyCauseReplaceMods(reasons: Seq[TThing] = this.reasons, result: TThing = this.result, mods: Set[Sense] = this.mods): this.type = {
    copyAttrs( newInstance(reasons, result, mods) )
  }

  override def copyReplaceMods(mods: Set[Sense]): this.type =
    copyCauseReplaceMods(this.reasons, this.result, mods)

  override def copySetObj(newObj: TThing): this.type = {
    copyCauseAddMods()
  }

  override protected def explainElements(pred: TPred): Option[TThing] = {
    hasArgM = commonExplainElements(pred)
    if( hasArgM )
      Some(this.copyCauseAddMods(
        reasons = reasons.map(_.fm()),
        result = result.fm()))
    else None
  }

  def genArgInf: Set[TPred] = reasons.size match {
    case 1 => argInfV2argsFunc[TThing, TThing] (sbj, obj)
    case 2 => argInfV3argsFunc[TThing, TThing, TThing] (sbj, obj, arg3)
    case 3 => argInfV4argsFunc[TThing, TThing, TThing, TThing] (sbj, obj, arg3, arg4)
  }

  override def toString: String = {
    ( predModStr + " " + nonePredModStr + " " + reasons + " causes " + result )
      .replace("ArrayBuffer(","")
      .replace("WrappedArray(","")
      .replace("List(","")
      .replace(")","")
  }



}
