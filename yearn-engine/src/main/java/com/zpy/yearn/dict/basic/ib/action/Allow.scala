/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Allow.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.verb.action.vt.Agree
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 允许：有的动作需要他人的允许才能执行，否则是不应该的
  *
  * 允许=你可以。。，
  * Created by zpy on 2019/9/22.
  */
case class Allow(override val actor: TIb, override val obj: TThing ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    val agree = Agree(actor, obj )
    infs_+=(vt.Need(obj, agree ), SourceType.EXPLAIN_INF)
    Set( agree  )
  }
}
object Allow extends StaticSense{
  override val words: String = "允许"
}
