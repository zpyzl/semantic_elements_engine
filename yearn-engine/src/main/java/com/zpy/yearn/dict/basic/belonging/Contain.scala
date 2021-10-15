/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Contain.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.belonging

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 包括，就是主语的一部分和宾语相同
  * Created by zpy on 2018/11/30.
  */
case class Contain(override val sbj: TThing, override val obj: TThing
 ) extends VT {
  sbj consists obj


  override def verbMeaning(pred: TPred): Set[TPred] =
    Set(Be( obj, Part().a().of(sbj)))

  //in consists, assign properties to objs
  // extreme standard contains eval self
  // part of extreme standard is eval self
  //conform: extreme eval self
  //eval
  //override val chStr: String = "包含 包括"
}
object Contain extends StaticSense{
  override val words: String = "包含 包括"
}
