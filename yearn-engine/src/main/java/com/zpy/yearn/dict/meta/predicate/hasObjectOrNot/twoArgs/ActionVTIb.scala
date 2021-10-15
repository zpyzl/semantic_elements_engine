/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ActionVTIb.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/5/19.
  */
trait ActionVTIb extends ActionVT {
  val objIb: TIb
  override val obj: TThing = objIb


  override def genArgInf: Set[TPred] = argInfV2argsFunc[TIb,TIb](sbj,objIb)
}
