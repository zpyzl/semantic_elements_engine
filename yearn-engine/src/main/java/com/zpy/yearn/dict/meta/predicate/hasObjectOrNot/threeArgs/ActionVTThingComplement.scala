/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActionVTThingComplement.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V3args}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}

/**
  * Created by zpy on 2019/5/19.
  */
abstract class ActionVTThingComplement extends ActionVT with V3args {
  val complement: TThing
  override val arg3: TThing = complement
  arg3.predTakingThis = Some(this )
  arg3.from = (SourceType.AS_ARG3, Set(this))



  override def genArgInf: Set[TPred] = argInfV3argsFunc[TIb,TThing,TThing](sbj,obj,complement)
}
