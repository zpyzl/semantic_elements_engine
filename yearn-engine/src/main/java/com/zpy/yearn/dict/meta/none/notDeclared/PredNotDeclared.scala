/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PredNotDeclared.scala
 * Date:2020/5/7 上午11:17
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.none.notDeclared

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.none.TNone
import com.zpy.yearn.dict.meta.predicate.linkOrNot.Verb

/**
  * Created by zpy on 2020/5/7.
  */
case class PredNotDeclared() extends Verb with TNone{
  override def genArgInf: Set[TPred] = Set()

}
