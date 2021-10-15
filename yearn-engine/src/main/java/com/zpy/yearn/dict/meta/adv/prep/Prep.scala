/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Prep.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.prep

import com.zpy.yearn.dict.meta.hasArgs.ElementName.ElementName
import com.zpy.yearn.dict.meta.hasArgs.{ElementName, HasElements, HasNamedElements, TPred}
import com.zpy.yearn.dict.meta.other.{Predicative, Sense}
import com.zpy.yearn.dict.meta.thing.AttrsCompareMethod.AttrsCompareMethod
import com.zpy.yearn.dict.meta.thing.{AttrsCompareMethod, TThing}

/**
  * Created by zpy on 2019/4/19.
  */
trait Prep extends Predicative with HasNamedElements{
  val obj: TThing

  override def elementsBeforeFilter: Set[Sense] = Set(obj)

  override def namedElementsBeforeFilter: Map[ElementName, _ <: Sense] = Map(
    ElementName.obj -> obj
  )
  override def is(that: Sense,attrsCompareMethod: AttrsCompareMethod): Boolean = {
    that match {
      case thatPrep: Prep => this.getClass == thatPrep.getClass && this.obj.is(thatPrep.obj, attrsCompareMethod)
      case _ => false
    }
  }

  def explain(pred: TPred): Boolean = {
    selfMeaning(pred)
  }

  def selfMeaning(pred: TPred): Boolean = {false}

  override def toString: String = super.toString + " " + obj

  def newInstance(obj: TThing ): this.type =
    this.getClass.getConstructors.filter(_.getParameterCount == 1).head.newInstance( obj).asInstanceOf[this.type]

  override def copyReplaceMods(mods: Set[Sense]): Prep.this.type =
    copyAttrs(newInstance(obj))

  override def setOrderSensitiveProps(isMainPred: Boolean = false): Unit = {
    obj.predTakingThis = this.predTakingThis
    obj.setOrderSensitiveProps()
  }


  override def setProps(stc: TPred): Unit = {
    obj.setProps(stc)
  }
}
