/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:OneOrMore.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.amount.absolute

import com.zpy.yearn.dict.meta.modifier.{Adj, TQuantifier}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.abstractNoun.Thing

/**
  * 一些
  * Created by zpy on 2019/10/6.
  */
case class OneOrMore[T <: TThing ](thing: T = Thing().some()) extends Adj with TQuantifier{
  val set: Set[T] = Set()

}
