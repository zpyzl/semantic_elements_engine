/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeAddictedTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt.beAdjPrep

import com.zpy.yearn.dict.adv.time.Often
import com.zpy.yearn.dict.basic.amount.relative
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.basic.normal.{NormalCompareTemp, NormalStateTarget}
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.vt.Like

/**
  * Created by zpy on 2019/10/6.
  */
case class BeAddictedTo(override val actor: TIb, override val obj: TThing ) extends ActionVT {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    val tempPred = Like(actor, obj)
      .which(obj.asInstanceOf[TPred].copyReplaceMods(
        Set(Often().which(logic.More(relative.Than(NormalCompareTemp()))))
      ))
    Set(Like(actor, obj)
      .which(obj.asInstanceOf[TPred].copyReplaceMods(
        Set(Often().which(logic.More(relative.Than(NormalStateTarget().of(tempPred))))
      ))))
  }
}
