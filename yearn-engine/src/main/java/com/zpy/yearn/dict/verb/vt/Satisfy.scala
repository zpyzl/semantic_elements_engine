/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Satisfy.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Content

/**
  * Created by zpy on 2019/10/6.
  */
case class Satisfy(override val sbj: TThing, desire: Desire ) extends VT {
  override val obj: TThing = desire
  //使欲望的内容“I want sth will happen”变成 sth happened
  override def verbMeaning(pred: TPred): Set[TPred] = {
    val desireContentOp = Content[TPred]().of(desire).get()
    if( desireContentOp.isDefined) {
      Set(Let(sbj,
        desireContentOp.get.did()))
    }else Set()

  }
}
