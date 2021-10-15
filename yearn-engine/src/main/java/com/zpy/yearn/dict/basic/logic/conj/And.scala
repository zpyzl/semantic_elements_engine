/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:And.scala
 * Date:2020/1/3 下午12:04
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.conj

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/11/6.
  */
case class And(override val sbj: TThing, override val obj: TThing
               ) extends VT {

}
object And{
  def and(a: TThing, things: Traversable[_ <: TThing]) : TThing = {
    things.foldLeft(a)( And(_,_ ))
  }
}
