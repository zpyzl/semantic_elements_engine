/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:See.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.sense

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/2/20.
  */
case class See(override val actor: TIb, override val obj: TThing
 ) extends ActionVT {
  //override val chStr: String = "看见 瞧见"
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      SenseAction(actor, obj ).use_+=( Eye().of(actor))
    )
  }
}
