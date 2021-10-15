/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Manage.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.Normal
import com.zpy.yearn.dict.noun.ib.role.Leader

import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.dict.verb.vt

/**
  * Created by zpy on 2019/10/9.
  */
case class Manage(override val actor: TIb, override val obj: TThing ) extends ActionVT {

  override def selfMeaning(pred: TPred): Boolean = {
    val manageAction = Do(actor)
    nature_+=(manageAction,pred)
    nature_+=(Be(actor, Leader().a().of(obj)),pred)
    nature_+=(vt.Let(manageAction, Be(obj, Normal())),pred)
    true
  }
}
