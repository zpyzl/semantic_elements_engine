/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Own.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.belonging

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 拥有
  * Created by zpy on 2019/10/5.
  */
case class Own(override val sbj: TThing, override val obj: TThing ) extends VT {

  override def explain(): Unit =
    super.explain()
}
