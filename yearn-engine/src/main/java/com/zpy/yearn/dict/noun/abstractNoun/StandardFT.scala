/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:StandardFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.noun.NounFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.predicate.fromTree.WayFT

/**
  * Created by zpy on 2018/12/12.
  */
class StandardFT(override val twp: Twp) extends SenseFT(twp) with NounFT with WayFT{
  override val meaning: TEntity[_] =
    new Standard()
}
