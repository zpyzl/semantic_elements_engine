/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Damage.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.amount.absolute.OneOrMore
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.{TPred, TEntity}
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.none.notDeclared.NotDeclared
import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.ib.Miserable
import com.zpy.yearn.dict.modifier.adj.thing.Normal
import com.zpy.yearn.dict.noun.abstractNoun.Value

/**
  * 伤害，损害，破坏
  *
  * 例句：
  * 他伤害了我们的友谊
  * 问：他做了什么不好的事？
  *
  *
  * Created by zpy on 2019/9/19.
  */
case class Damage(override val sbj: TThing, override val obj: TThing = NotDeclared() ) extends VT {
  override def selfMeaning(pred: TPred): Boolean = {
    /*
    */
    obj match {
      case vcObj: VC =>
        this.nature_+=(
          Cause( (sbj), Be(vcObj, Normal()).not()),pred)//使状态不正常
      case ib: TIb => //伤害人，就是使他痛苦
        Let(sbj, Be(sbj, Miserable() ) )
      case entityObj: TEntity[_] =>
        this.nature_+=(
          Value().which(OneOrMore()).of(entityObj).notExist(),pred)
        //todo 命题中的Value是Abstract，那么查找其指代，找到后替换Value
      case _ => throw new RuntimeException(s"Damage's obj - $obj is neither VC nor Entity!")

    }
    true
  }

  //override val chStr: String = "伤害 损害 破坏"
}
