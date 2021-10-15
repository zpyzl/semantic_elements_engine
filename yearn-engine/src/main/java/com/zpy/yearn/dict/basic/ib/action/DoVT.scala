/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:DoVT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.{TActionInstance, TThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/2.
  */
case class DoVT(override val actor: TIb, override val obj: TThing

                ) extends ActionVT with  TActionInstance {
  //override val chStr: String = "做"
}
object DoVT extends StaticSense{
  override val words: String = "做"
}