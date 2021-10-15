/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DeterminerFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.modifier.ModifierFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/1/22.
  */
class DeterminerFactory(override val twp: Twp, override val central: TThing) extends SenseFT(twp) with ModifierFT {
/*
  val determiner: Modifier = wordStr match {
    case "有些" => com.zpy.yearn.dict.modifier.determiner.OneOrMore()
  }
  determiner.twp_=( Some(twp)
  mods_=( central, central.mods ++ Set( determiner))*/
}
