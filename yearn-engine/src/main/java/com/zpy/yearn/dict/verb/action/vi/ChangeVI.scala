/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ChangeVI.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vi

import com.zpy.yearn.dict.basic.verb.Status
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vt.ChangeFromTo

/**
  * Created by zpy on 2019/10/6.
  */
case class ChangeVI(override val sbj: TThing ) extends VI {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    val toStatus: VC = Status(sbj)
    Set(
      ChangeFromTo(
        toStatus.not(),
        toStatus)
    )
  }
}
