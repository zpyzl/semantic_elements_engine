/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Become.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{LinkV, TPred}
import com.zpy.yearn.dict.meta.other.Predicative
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.thing.From
import com.zpy.yearn.dict.verb.vt.ChangeFromTo
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/14.
  */
case class Become(override val sbj: TThing,
                  override val predicative: Predicative
 ) extends LinkV  {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    val fromStatus = pred.mods.filter(_.isInstanceOf[From]).map(_.asInstanceOf[From])
    if( fromStatus.size > 1) throw new RuntimeException("In Become multi From not supported!")
    Set(
      ChangeFromTo(

        if(fromStatus.nonEmpty) Be(sbj, fromStatus.head)
        else Be(sbj, predicative).not(),

        Be(sbj, predicative)))
  }
}
object Become extends StaticSense{
  override val words: String = "变得 变成"
}
