/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Target.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun.human

import com.zpy.yearn.dict.basic.belonging.Have
import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.structure.sense.StaticSense

/**
  *
  * 问：他有什么目标？
  * 答：他想学英语
  *
  *
  * P1: 他有什么计划？
  * P2: 他觉得他应该减肥
  *
  * P2 & 他觉得他应该=>他想要 => 他想要减肥
  *
  * Created by zpy on 2019/10/29.
  */
case class Target() extends TEntityOfIb{
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some({
      val targetAct = Do(owner())
      val desire = Desire()
      targetAct.which(
        Have(owner(),
          desire.which(Be(Central(), targetAct))))
    })
  }
}
object Target extends StaticSense{
  override val words: String = "目标 目的"
}
