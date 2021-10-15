/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VTStatus.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/4/18.
  */
abstract class VTStatus extends V2args {
  val statusObj: VC
  override val obj: TThing = statusObj


  override def genArgInf: Set[TPred] = argInfV2argsFunc[TThing,VC](sbj,statusObj)
}
