/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Happen.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.time

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vt.ChangeFromTo

/**
  * Created by zpy on 2019/10/6.
  */
case class Happen(override val sbj: TThing ) extends VI {
  override def verbMeaning(pred: TPred): Set[TPred] =
    sbj match {
      case pred: TPred =>
        Set(pred.did())
      case _ => Set()
        //throw new RuntimeException("Happen's sbj is not Pred!")
    }
}
