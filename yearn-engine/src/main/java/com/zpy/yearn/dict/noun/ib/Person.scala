/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Person.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.modifier.Definite

/**
  * Created by zpy on 2018/11/10.
  */
case class Person(nameStr: String) extends TIb {
  name = nameStr
  if( name.nonEmpty) definite = Definite.the

  override def toString: String = if( name.nonEmpty) name else super.toString

  override def selfMeaning(pred: TPred): Boolean = super.selfMeaning(pred)
  //override val chStr: String = "人"
}
