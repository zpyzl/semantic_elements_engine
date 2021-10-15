/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Need.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.belonging.Part
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  *
  * 例句：
  * 我们创业需要一个会计
  *
  * Created by zpy on 2019/7/26.
  */
case class Need(override val sbj: TThing, override val obj: TThing ) extends VT{
  override def selfMeaning(pred: TPred): Boolean = {
    nature_+=(Cause((obj), Part().a().of(sbj)),pred)
    true
  }

  //override val chStr: String = "需要"
}
object Need extends StaticSense{
  override val words: String = "需要"
}