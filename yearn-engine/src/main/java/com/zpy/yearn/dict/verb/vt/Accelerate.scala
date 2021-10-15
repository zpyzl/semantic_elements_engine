/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Accelerate.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.amount.relative
import com.zpy.yearn.dict.basic.time.Speed
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/2.
  */
case class Accelerate(override val sbj: TThing, override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set( new Cause( sbj, relative.Increase(Speed().of(obj)) ))
  }

  //override val chStr: String = "加速 加快"
}
