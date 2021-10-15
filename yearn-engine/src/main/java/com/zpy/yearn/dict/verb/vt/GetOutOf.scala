/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:GetOutOf.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/28.
  */
case class GetOutOf(override val sbj: TThing, override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      ChangeFromTo( logic.Be(sbj, obj), logic.Be(sbj, obj).not() )
    )
  }
}
object GetOutOf extends StaticSense{
  override val words: String = "脱离"
}