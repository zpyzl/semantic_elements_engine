/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Begin.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vi

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vt.ChangeFromTo

/**
  * Created by zpy on 2019/10/5.
  */
case class Begin(vcSbj: VC ) extends VI {
  override val sbj: TThing = vcSbj



  override def verbMeaning(pred: TPred): Set[TPred] =
    Set( ChangeFromTo(vcSbj.not(), vcSbj ))
}
