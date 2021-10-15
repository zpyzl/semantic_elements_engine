/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Mass.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib

import com.zpy.yearn.dict.basic.amount.absolute.All
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.other.Sense
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.noun.concrete.World
import com.zpy.yearn.structure.sense.StaticSense

/**
  *
  * Created by zpy on 2019/10/24.
  */
case class Mass(override val mods: Set[Sense]=Set()) extends TIb {
  override def nounMeaning(pred: TPred): Option[TThing] = {
    Some( People().which(All().in(World())))
  }

}
object Mass extends StaticSense{
  override val words: String = "大众"
}
