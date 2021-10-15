/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:GetBalanced.scala
 * Date:2020/2/9 上午11:06
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vi

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/2/9.
  */
case class GetBalanced(override val sbj: TThing) extends VI{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(

    )
  }
}
