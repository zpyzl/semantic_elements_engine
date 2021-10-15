/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Movement.scala
 * Date:2020/4/27 下午4:26
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.auxVerb

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.linkOrNot.Verb
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/4/27.
  */
case class Movement(override val sbj: TThing) extends Verb {
  override def genArgInf: Set[TPred] = argInf1ArgFunc[TThing](sbj)
}
