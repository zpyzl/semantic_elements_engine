/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:IfThen.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.conj
import com.zpy.yearn.dict.meta.hasArgs.{TPred, V2args}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/9/5.
  */
case class IfThen(cond: TPred, result: TPred ) extends V2args with Conj {
  override val sbj: TThing = cond
  override val obj: TThing = result
  override def genArgInf: Set[TPred] = argInfV2argsFunc[TPred, TPred] (cond, result)

  override def toString: String = "If " + cond + ", " + result

  override def verbMeaning(pred: TPred): Set[TPred] = Set()

  //override val chStr: String = "如果"
}
