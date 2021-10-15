/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:EmptyNonePredFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.noun.NounFT
import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.noun.abstractNoun.Entity

/**
  * 需要返回NounFT但又没有实际内容时占位用
  * Created by zpy on 2019/3/2.
  */
class EmptyNonePredFT(override val twp: Twp) extends SenseFT(twp) with NounFT {
  override val meaning: TEntity[_] = Entity()
}
