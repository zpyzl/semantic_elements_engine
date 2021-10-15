/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Start.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.time.Early
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.prep.thing.In

import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 发起
  * Created by zpy on 2019/10/5.
  */
case class Start(override val actor: TIb, vcObj: VC ) extends ActionVT {
  override val obj: TThing = vcObj



  override def selfMeaning(pred: TPred): Boolean = {
    val startAction = Do(actor )
    nature_+=( startAction,pred)
    nature_+=(  vt.Belong(startAction, vcObj),pred )
    nature_+=(  Be(startAction, Early().which(logic.Most(Right(In(vcObj))))),pred)
    true
  }
}
object Start extends StaticSense{
  override val words: String = "发起 开始"
}
