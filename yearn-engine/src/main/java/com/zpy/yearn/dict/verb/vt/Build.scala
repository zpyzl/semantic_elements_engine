/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Build.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vt

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/9/2.
  */
case class Build(override val sbj: TThing, override val obj: TThing ) extends VT {
  //override val chStr: String = "建造"
}
object Build extends StaticSense{
  override val words: String = "建造"
}