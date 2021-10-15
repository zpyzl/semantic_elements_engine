/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Repeat.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.basic.time.Repeatedly
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/8.
  */
case class Repeat(override val sbj: TThing, override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    obj match{
      case predObj: TPred => Set( predObj.copyReplaceMods(Set(Repeatedly())))
      case _ => throw new RuntimeException("Repeat's obj is not a Pred!")
    }
  }
}
