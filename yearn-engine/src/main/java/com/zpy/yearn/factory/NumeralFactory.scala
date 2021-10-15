/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:NumeralFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.modifier.ModifierFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2018/11/29.
  */
class NumeralFactory(override val twp: Twp, override val central: TThing) extends SenseFT(twp) with ModifierFT {
    central.num = wordStr match {
      case "一" => 1
      case "两" => 2
      case "三" => 3

    }
}
