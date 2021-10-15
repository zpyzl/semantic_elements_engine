/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:StatusVI.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/9/27.
  */
abstract class StatusVI extends VI {
  val statusObj: VC
  override val sbj: TThing = statusObj

  override def genArgInf: Set[TPred] = argInf1ArgFunc[VC](statusObj)
}
