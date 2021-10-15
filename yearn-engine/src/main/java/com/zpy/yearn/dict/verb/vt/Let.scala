/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Let.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 使，让
  * 1. let sb do sth
  *
  * Created by zpy on 2019/8/16.
  */
case class Let(override val sbj: TThing, override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] =
    Set( new Cause( sbj, obj ))
}
object Let extends StaticSense{
  override val words: String = "使 让"
}