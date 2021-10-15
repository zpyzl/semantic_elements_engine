/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeWorth.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.normal.Should
import com.zpy.yearn.dict.meta.hasArgs.{TEntity, TPred}
import com.zpy.yearn.dict.meta.ib.{TAction, TIb}
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.verb.action.vt
import com.zpy.yearn.dict.verb.action.vt.Pay
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/11.
  */
case class BeWorth(override val sbj: TThing, override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = {
    val target: TAction = sbj match {
      case entitySbj: TEntity[_] => vt.Get( pred.twp.get.sentence.defaultSbjs.get.head.asInstanceOf[TIb]  ,entitySbj )
      case _ => sbj.asInstanceOf[TAction]
    }
    val pay: TAction = obj match {
      case entityObj: TEntity[_] => Pay( pred.twp.get.sentence.defaultSbjs.get.head.asInstanceOf[TIb], entityObj)
      case _ => obj.asInstanceOf[TAction]
    }

    //为了。。付出是应该的
    Set( pay.inOrderTo(target).copyReplaceMods(Set(Should())))
  }
}
object BeWorth extends StaticSense{
  override val words: String = "值得"
}