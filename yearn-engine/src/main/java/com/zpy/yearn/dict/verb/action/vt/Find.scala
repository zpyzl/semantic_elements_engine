/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Find.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.action
import com.zpy.yearn.dict.basic.ib.sense.SenseAction
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VNoneCont
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.{Abstract, Concrete}
import com.zpy.yearn.dict.verb.action.vi
import com.zpy.yearn.dict.verb.action.vi.Begin

/**
  * Created by zpy on 2019/11/2.
  */
case class Find(override val actor: TIb, override val obj: TThing) extends ActionVT with VNoneCont{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      obj match {
        case concrete: Concrete =>
          vi.Begin( SenseAction(actor, obj))
        case abstractObj: Abstract =>
          Begin( action.Know(actor,  obj ))
      }
    )
  }
}
