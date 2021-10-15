/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Feel.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.sense

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.other.Predicative
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/8.
  */
case class Feel(actor: TIb, feeling: Adj  ) extends LinkV  {
  override val sbj: TThing = actor
  override val predicative: Predicative = feeling

  override def verbMeaning(pred: TPred): Set[TPred] =
    Set( SenseAction(actor, Be(actor, feeling)))
}
object Feel extends StaticSense{
  override val words: String = "感觉 觉得"
}
