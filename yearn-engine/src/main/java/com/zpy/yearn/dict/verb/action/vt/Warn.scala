/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Warn.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.Dangerous

/**
  * Created by zpy on 2019/10/29.
  */
case class Warn(override val actor: TIb, override val obj: TThing ) extends ActionVT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set(
      Express( actor,
        obj.copyAddMods(Set(logic.Be(Central(), Dangerous()))) )
    )
  }
}
