/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Admire.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.{Good, action}
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.auxVerb
import com.zpy.yearn.structure.sense.StaticSense

/**
  *
  * Created by zpy on 2019/10/18.
  */
case class Admire(override val actor: TIb, override val obj: TThing ) extends ActionVT {
  override def selfMeaning(pred: TPred): Boolean = {
    this.nature_+=( action.Think(actor, logic.Be(obj, Good())),pred)
    this.nature_+=( auxVerb.Want(actor, obj),pred)//todo 把obj中的他人换成自己
    true
  }

}

object Admire extends StaticSense{
  override val words: String = "羡慕"
}
