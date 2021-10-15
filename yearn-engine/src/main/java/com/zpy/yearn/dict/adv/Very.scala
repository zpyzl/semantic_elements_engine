/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Very.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.basic.ib.Good
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/5/6.
  */
case class Very( ) extends AdjAdv {

  //adj.inf( VERY() ).map(this.eqvls_+=)

  override def asAdj(thing: TThing): Option[Adj] = Some(Good())

  override def adjAdvMeaning(pred: TPred): Option[AdjAdv] = None
}
object Very extends StaticSense{
  override val words: String = "非常 很"
}
