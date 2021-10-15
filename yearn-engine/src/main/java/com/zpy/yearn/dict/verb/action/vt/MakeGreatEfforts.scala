/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:MakeGreatEfforts.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.amount.absolute.ALot
import com.zpy.yearn.dict.basic.ib.effort.Effort
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTAction
import com.zpy.yearn.dict.verb.action.way
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/29.
  */
case class MakeGreatEfforts(override val actor: TIb, override val actionObj: TAction) extends ActionVTAction {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      way.Use( actor, Effort().which(ALot()), actionObj)
    )
  }
}
object MakeGreatEfforts extends StaticSense{
  override val words: String = "努力"
}
