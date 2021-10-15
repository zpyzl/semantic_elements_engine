/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AllOfPreds.scala
 * Date:2020/1/3 下午12:04
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.conj

import com.zpy.yearn.dict.meta.hasArgs.{TPred, V4args}
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.Nothing

/**
  * Created by zpy on 2019/10/28.
  */
case class AllOfPreds(preds: Set[TPred] ) extends V4args {
  if( preds.size > 4 )
    throw new RuntimeException("MultiPred which has > 4 preds are not supported!")

  preds.foreach(p => {
    p.from = (SourceType.AS_ITEM, Set(this))
    p.predTakingThis = Some(this)
  } )


  val predsSeq: Seq[TPred] = preds.toSeq
  override val sbj: TThing = predsSeq.head
  override val obj: TThing = predsSeq(1)

  override val arg3: TThing =
    if( preds.size >= 3 ) predsSeq(2)
    else Nothing()

  override val arg4: TThing =
    if( preds.size >= 4 ) predsSeq(3)
    else Nothing()

  override def newInstance(sbj: TThing, obj: TThing, arg3: TThing, arg4: TThing, mods: Set[Sense]): AllOfPreds.this.type = {
    val r: AllOfPreds = AllOfPreds(Set(sbj, obj, arg3, arg4).filter(!_.isInstanceOf[Nothing]).map(_.asInstanceOf[TPred]))
    r.mods_=(mods)
    r.asInstanceOf[this.type]
  }


  def genArgInf: Set[TPred] = preds.size match {
    case 2 => argInfV2argsFunc[TThing, TThing] (sbj, obj)
    case 3 => argInfV3argsFunc[TThing, TThing, TThing] (sbj, obj, arg3)
    case 4 => argInfV4argsFunc[TThing, TThing, TThing, TThing] (sbj, obj, arg3, arg4)
  }

}
