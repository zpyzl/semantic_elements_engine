/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeSimilarTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.relation

import com.zpy.yearn.dict.basic.amount.absolute.OneOrMore
import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 相像
  * Created by zpy on 2019/10/6.
  */
case class BeSimilarTo(override val sbj: TThing, override val obj: TThing ) extends VT {

  override def selfMeaning(pred: TPred): Boolean = {
    OneOrMore( belonging.Property().of(sbj)).set.map(
      BeTheSameTo(
        _,
        belonging.Property().a().of(obj)
    )).foreach( nature_+=(_,pred))
    true
  }

}
