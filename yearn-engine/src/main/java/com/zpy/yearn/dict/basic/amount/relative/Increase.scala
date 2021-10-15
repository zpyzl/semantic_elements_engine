/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Increase.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.amount.relative

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 增加，增大
  * Created by zpy on 2019/10/2.
  */
case class Increase(override val sbj: TThing  ) extends VI {
  //override val chStr: String = "增加 提高 增大"
}
