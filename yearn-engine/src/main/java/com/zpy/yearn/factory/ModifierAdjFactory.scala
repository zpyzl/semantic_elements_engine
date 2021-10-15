/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ModifierAdjFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.modifier.{Adj, AdjFT, AdjStr, ModifierFT}
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.modifier.adj.thing.{Basic, Complete, Extreme, Useless}

/**
  * 定语形容词
  * Created by zpy on 2018/10/28.
  */
class ModifierAdjFactory(override val twp: Twp, override val central: TThing) extends AdjFT(twp) with ModifierFT  {

  central.mods_=( central.mods + adj )
}