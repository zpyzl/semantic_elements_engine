/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Try.scala
 * Date:2020/2/7 下午10:28
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing

import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.auxVerb.Want

/**
  * 例句：
  * 问：他想学英语吗？
  * 答：他试图学英语
  *
  * want learn?
  *
  * try learn =>
  * sa, want learn cause sa
  *
  * Created by zpy on 2020/2/7.
  */
case class Try(override val actor: TIb, override val obj: TThing) extends ActionVT{
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      Do(actor)
        .which(new Cause(Central(),obj))
        .because(Want(actor, obj))
    )
  }
}
