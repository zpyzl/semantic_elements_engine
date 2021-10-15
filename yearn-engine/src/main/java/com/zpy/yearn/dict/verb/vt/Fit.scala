/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Proper.scala
 * Date:2020/2/7 下午6:06
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.vi.Succeed

/**
  * sth适合v, sth可以导致v成功
  *
  *
  *
  *
  * 和因果的关系：
  * 问：他适合创业吗？为什么？
  * 答：他没有激情。只有有激情的人才能创业成功
  * why he not conform need of startComp?
  * -=> why he not conform ( sth cause startComp)
  *
  * 对why，判断he not conform ( sth cause startComp)目标1是否正确
  * ==(语料通用处理) 确定sth，根据其mods，找到==
  * only sb who has passion cause startComp 原因1
  * sth = sb who has passion
  *
  * 目标1 <=> he not conform sb who has passion 等价2
  * & he has no passion 原因2 （==通用：命题中事物寻找并加上性质==）
  * <=> (he has no passion ) is not sb who has passion 等价1
  * 判断等价1是正确的
  * 要找目标1的原因，找其等价-等价1的原因。
  * 等价1，是由——目标1、原因1、原因2、等价2，其中目标1、等价2是等价1的等价，不算。则等价1的原因是原因1、原因2。即目标1的原因
  *
  * Created by zpy on 2020/2/6.
  */
case class Fit(override val sbj: TThing, override val obj: TThing) extends VT{
  override def verbMeaning(pred: TPred): Set[TPred] =
  {
    Set(
      new Cause(sbj, Succeed(obj)).which(Can())
    )
  }
}
