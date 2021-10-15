/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Leave.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.thing

/**
  * Created by zpy on 2019/10/28.
  */
case class Leave(override val sbj: TThing, override val obj: TThing, override val mods: Set[Sense]= Set() ) extends VT{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      ChangeFromTo(
        logic.Be(sbj, thing.In(obj)),
        logic.Be(sbj, thing.In(obj)).not())
    )
  }
}
