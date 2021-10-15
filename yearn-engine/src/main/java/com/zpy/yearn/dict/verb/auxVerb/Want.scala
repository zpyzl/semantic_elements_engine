/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Want.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.auxVerb

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.belonging.Have
import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.time.Will
import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/1/22.
  */
case class Want(override val actor: TIb, override val obj: TThing
               ) extends ActionVT {

  def this(action: TAction) = {
    this(action.actor, action)
  }

  override def verbMeaning(pred: TPred): Set[TPred] = {
    //实际上宾语都是will形式，但是可能原始形式利于推理，所以保留原始形式
    val will: TPred = objAsWill

    Set(Have(actor, Desire().of(actor).whichIs(will)))
  }

  /**
    * 宾语规范为will形式的宾语
    *
    * @return
    */
  def objAsWill: TPred = {
    obj match {
      case _: TEntity[_] =>
        (belonging.Have(actor, obj).which(Will()))
      case predObj: TPred if !obj.mods.contains(Will()) =>
        (predObj.copyReplaceMods(Set(Will())))
      case predObj: TPred if predObj.mods.contains(Will()) => (predObj)
      case _ =>
        throw new RuntimeException("invalid obj of Want!")
    }
  }
}

object Want extends StaticSense {
  override val words: String = "要 想 想要"
}
