/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Tense.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.tense

/**
  * Created by zpy on 2018/11/27.
  */
object Tense extends Enumeration {
  type Tense = Value
  val SimplePresent,SimplePast,SimpleFuture,
  PresentContinuous,PastContinuous,//Perfect完成时，Continuous进行时
  PresentPerfect,PresentPerfectContinuous,
  PastPerfect, PastPerfectContinuous,
  FutureContinuous,FuturePerfect,FuturePerfectContinuous,
  UsedTo,WouldAlways,
  FutureInThePast // would , was going to.
  = Value

  def present: Set[Tense] = Set(SimplePresent, PresentContinuous, PresentPerfect, PresentPerfectContinuous )
  def past: Set[Tense] = Set(SimplePast, PastContinuous, PastPerfect, PastPerfectContinuous, FutureInThePast)
}
