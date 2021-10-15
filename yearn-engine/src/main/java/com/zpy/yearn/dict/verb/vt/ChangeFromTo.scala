/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ChangeFromTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 我们部门的人数由3个人变成了10个人
  *
  * Created by zpy on 2019/4/18.
  */
case class ChangeFromTo(fromStatus: VC, toStatus: VC
                        ) extends VT {
  override val sbj: TThing = fromStatus
  override val obj: TThing = toStatus

  override def genArgInf: Set[TPred] = argInfV2argsFunc[VC,VC](fromStatus, toStatus)

  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(Cause((sbj), obj))
  }


  //override val chStr: String = "变为 变成"
}

