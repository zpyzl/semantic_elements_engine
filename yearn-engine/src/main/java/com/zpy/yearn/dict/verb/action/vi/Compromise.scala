/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Compromise.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vi

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.ActionVI
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.action.vt.Abandon
import com.zpy.yearn.dict.verb.auxVerb

/**
  * Created by zpy on 2019/9/27.
  */
case class Compromise(override val actor: TIb ) extends ActionVI {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    Set( Abandon(actor, Thing().some().which( auxVerb.Want(actor, Central()) ) ))
  }

  //override val chStr: String = "妥协 委曲求全"
}
