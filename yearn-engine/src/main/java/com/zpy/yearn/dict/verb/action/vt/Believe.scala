/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Believe.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.basic.ib.{Thought, action}
import com.zpy.yearn.dict.basic.logic.{Be, True}
import com.zpy.yearn.dict.meta.hasArgs.{TPred, TEntity}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.{Content, Thing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/5/18.
  */
case class Believe(override val actor: TIb, override val obj: TThing
 ) extends ActionVT {

  override def selfMeaning(pred: TPred): Boolean = {
    obj match {
      case ib: TIb =>
        this.nature_+=(Believe(actor, Thought().of(ib)),pred)
        true
      case _: TPred  =>
        this.nature_+=( action.Know( actor, Be( obj, True())),pred)
        true
      case entity@(_: TEntity[_] | _: Thing )=> //todo for something, 可能是Pred的case也可能是Entity的case
        nature_+=( action.Know(actor, Be( Content().of( entity) , True())), pred)
        true

    }

  }

  //override val chStr: String = "相信"
}

object Believe extends StaticSense{
  override val words: String = "相信"
}