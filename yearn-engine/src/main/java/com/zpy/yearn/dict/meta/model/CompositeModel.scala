/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:CompositeModel.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.model

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * obj: 组成部分
  *
  * Created by zpy on 2019/4/27.
  */
case class CompositeModel(override val sbj: TThing,
                          override val obj: TThing ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = Set()

  //override val chStr: String = "CompositeModel"
}
