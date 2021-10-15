/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Live.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.action

import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 活着
  * Created by zpy on 2019/4/18.
  */
case class Live(override val sbj: TThing
                  ) extends VI with VC{
  //override val chStr: String = "活 生活"
}
object Live extends StaticSense{
  override val words: String = "活 生活"
}