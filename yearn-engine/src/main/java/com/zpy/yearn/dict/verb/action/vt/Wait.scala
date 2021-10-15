/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Wait.scala
 * Date:2020/3/6 下午8:03
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionVTComplement
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 不采取某行动，直到（被等的动作发生）
  * Created by zpy on 2020/3/6.
  */
case class Wait(override val actor: TIb, override val obj: TThing, override val complement: TPred) extends ActionVTComplement{

}
