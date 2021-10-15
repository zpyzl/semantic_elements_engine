/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Elements.scala
 * Date:2020/4/9 下午3:08
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.hasArgs

/**
  * Created by zpy on 2020/4/9.
  */
object ElementName extends Enumeration {
  type ElementName = Value
  val sbj, actor, obj, arg3, arg4, thing, ownerData, predicative,
  reason1, reason2, reason3, result = Value
}
