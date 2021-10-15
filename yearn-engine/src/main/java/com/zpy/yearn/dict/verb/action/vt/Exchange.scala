/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Exchange.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.belonging.Have
import com.zpy.yearn.dict.basic.logic.conj.And
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

import scala.collection.mutable

/**
  * Created by zpy on 2019/11/11.
  */
case class Exchange(override val actor: TIb, override val obj: TThing) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    val haveWhatToUse: mutable.Set[Have] = uses.map(Have(actor, _))
    val loseWhatToUse: mutable.Set[Have] = uses.map(Have(actor, _).not() )
    Set(
      new Cause( Seq(
        belonging.Have(actor, obj).not()) ++
        haveWhatToUse.toSeq,

        And.and(
          Have( actor, obj ),
          loseWhatToUse
        )
      )
    )
  }
}
