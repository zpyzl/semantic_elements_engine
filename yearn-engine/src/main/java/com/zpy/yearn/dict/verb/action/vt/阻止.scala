/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:阻止.scala
 * Date:2020/3/8 上午11:48
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.adv.Not
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.basic.time.Happen
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.predicate.continuous.{VC, VNoneCont}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.action.vi.Stop
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.dict.verb.vt.Let

/**
  * Created by zpy on 2020/3/8.
  */
case class 阻止(override val actor: TIb, override val obj: TThing) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = Set(
    Do().which(
      vt.Let(Central(),
        obj match {
          case vc: VC=> Stop(vc)
          case noneCont: VNoneCont =>
            Happen(noneCont).which(Can(),Not())
        }
      ))
  )
}
