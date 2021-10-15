/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VTConcrete.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.Concrete

/**
  * Created by zpy on 2019/3/13.
  */
abstract class VTConcrete extends V2args {
  val concreteObj: Concrete
  override val obj: TThing = concreteObj



  override def genArgInf: Set[TPred] =
    argInfV2argsFunc[TThing, Concrete](sbj, concreteObj)

}
