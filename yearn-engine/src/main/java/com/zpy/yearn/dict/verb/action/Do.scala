/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Action.scala
 * Date:2020/4/27 下午3:51
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.none.notDeclared.IbNotDeclared
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.ActionVI
import com.zpy.yearn.dict.meta.thing.{TActionInstance, TThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/4/27.
  */
case class Do(override val actor: TIb = IbNotDeclared()) extends ActionVI with  TActionInstance with VC {
  this.traitForAssignable = Some(classOf[TAction])

  override def genArgInf: Set[TPred] = argInf1ArgFunc[TThing](sbj)
}
object Do extends StaticSense{
  override val words: String = "做"
}