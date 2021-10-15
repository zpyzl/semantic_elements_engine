/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AppearInFrontOf.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.ib.sense
import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/11/11.
  */
case class AppearInFrontOf(override val sbj: TThing, override val obj: TThing) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      sense.See(obj match {
        case ib: TIb => ib
        case _ => defaultIbSbj.getOrElse(CommonIb())
      }, sbj )
    )
  }
}
