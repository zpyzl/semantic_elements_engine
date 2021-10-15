/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TakeChargeOf.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.ib.attr.Responsibility
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/11.
  */
case class TakeChargeOf(override val actor: TIb, override val obj: TThing ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set( Be( Responsibility().a().of(actor), obj ))
  }
}
object TakeChargeOf extends StaticSense{
  override val words: String = "负责"
}
