/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PrepFactory.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.factory

import com.zpy.yearn.dict.meta.other.{SenseFT, Twp}
import com.zpy.yearn.dict.meta.structuralWord.PrepFT
import com.zpy.yearn.dict.prep.thing
import com.zpy.yearn.dict.prep.thing.UsePrep
import com.zpy.yearn.structure.pos.Constituent

/**
  * Created by zpy on 2019/3/1.
  */
class PrepFactory(override val twp: Twp) extends SenseFT(twp) with PrepFT {
  def matchWord: AdvbPreps = {
    val objs = followers(Constituent.ADVERBIAL)
    val preps: AdvbPreps  = wordStr match {
      case "用" => Set() ++ objs.map(UsePrep)
      case "在" => Set() ++ objs.map( thing.In )
      case _ => Set()
    }
    preps.foreach(_.twp_=( Some(twp) ))
    preps
  }
  override val preps: AdvbPreps = matchWord


}
