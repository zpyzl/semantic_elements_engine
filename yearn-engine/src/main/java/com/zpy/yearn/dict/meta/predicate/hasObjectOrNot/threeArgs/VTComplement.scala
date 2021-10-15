/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:VTComplement.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V3args}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}

/**
  * Created by zpy on 2019/10/27.
  */
abstract class VTComplement extends VT with V3args {

  val complement: TPred
  override val arg3: TThing = complement
  arg3.predTakingThis = Some(this )
  arg3.from = (SourceType.AS_ARG3, Set(this))

  override def genArgInf: Set[TPred] = argInfV3argsFunc[TThing,TThing,TPred](sbj,obj,complement)
}
