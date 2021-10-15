/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:IbBoolean.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.common

/**
  * True, False, NotSure
  *
  * Created by zpy on 2019/2/21.
  */
object IbBoolean extends Enumeration {
  type IbBoolean = Value
  val True, False, NotSure = Value
}
