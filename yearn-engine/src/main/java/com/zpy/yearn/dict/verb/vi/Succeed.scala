/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Succeed.scala
 * Date:2020/2/22 下午9:25
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vi

import com.zpy.yearn.dict.basic.cause.Result
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.auxVerb

/**
  *
  * Created by zpy on 2019/4/14.
  */
case class Succeed(override val sbj: TThing //行动
                  ) extends VI {
  override def verbMeaning(pred: TPred): Set[TPred] = Set(
    auxVerb.Want( sbj.asInstanceOf[TAction].actor, Result().of(sbj) )
  )

  //override val chStr: String = "成功"
}
