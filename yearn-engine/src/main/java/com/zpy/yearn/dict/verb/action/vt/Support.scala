/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Support.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vt

/**
  * Created by zpy on 2019/10/28.
  */
case class Support(override val actor: TIb, override val obj: TThing
                   ) extends ActionVT {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      Agree(actor, obj.asInstanceOf[TAction].actor),
      vt.Help(actor, obj)
    )
  }
}
