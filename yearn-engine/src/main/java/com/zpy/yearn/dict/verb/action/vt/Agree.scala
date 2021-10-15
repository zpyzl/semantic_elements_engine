/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Agree.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.entity.Statement
import com.zpy.yearn.dict.basic.ib.action.Think
import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.way

/**
  * Created by zpy on 2019/9/27.
  */
case class Agree(override val actor: TIb, override val obj: TThing ) extends ActionVT {

  override def verbMeaning(pred: TPred): Set[TPred] = {
    obj match {
      case ib: TIb => Set(Agree(actor, Statement().of(ib)))
      case entity: TEntity[_] => Set( way.Use(obj = entity).which(Can()))
      case action: TAction => Set(
        action.should()
      )
      case pred: TPred => Set( Think( actor, pred ))
      case _ => throw new RuntimeException("agree's obj is wrong!")
    }
  }

  //override val chStr: String = "同意"
}
