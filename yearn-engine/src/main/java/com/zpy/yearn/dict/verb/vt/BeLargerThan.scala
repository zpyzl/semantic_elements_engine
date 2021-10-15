/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeLargerThan.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.amount.{Great, relative}
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/28.
  */
case class BeLargerThan(override val sbj: TThing, override val obj: TThing, override val mods: Set[Sense] = Set( )) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set( logic.
      Be( sbj,
        Great().which(logic.More( relative.Than( obj )) )) )
  }
}
