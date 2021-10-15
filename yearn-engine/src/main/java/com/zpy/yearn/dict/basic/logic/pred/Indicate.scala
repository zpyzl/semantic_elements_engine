/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Indicate.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.pred

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 说明
  * Created by zpy on 2019/9/26.
  */
case class Indicate(override val sbj: TThing, override val obj: TThing
                    ) extends VT {
  override def verbMeaning(pred: TPred): Set[TPred] = Set()

  //override val chStr: String = "说明"
}
