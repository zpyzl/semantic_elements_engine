/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeNot.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.logic

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred}
import com.zpy.yearn.dict.meta.other.Predicative
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/27.
  */
case class BeNot(override val sbj: TThing, override val predicative: Predicative ) extends LinkV {
  override def verbMeaning(pred: TPred): Set[TPred] = {

    Set( logic.Be( sbj, predicative ).not() )
  }
}
