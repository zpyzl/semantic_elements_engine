/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Expect.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.alias

import com.zpy.yearn.dict.basic.ib.action.Know
import com.zpy.yearn.dict.basic.time.{InTheFuture, Will}
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  *
  * Created by zpy on 2019/4/27.
  */
case class Expect(override val actor: TIb, override val obj: TThing
 ) extends ActionVT {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    val objClone = obj match {
      case will: Will => obj
      case obj => obj.copyReplaceMods(Set(InTheFuture()))
    }
    Set(Know(actor, objClone))
  }

  //override val chStr: String = "期望 预计"
}
