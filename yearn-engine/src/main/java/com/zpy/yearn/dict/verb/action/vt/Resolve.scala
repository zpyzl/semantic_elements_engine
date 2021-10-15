/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Resolve.scala
 * Date:2020/3/8 下午7:53
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVT
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/3/8.
  */
case class Resolve(override val actor: TIb,
                   override val obj: TThing) extends ActionVT{

}
object Resolve extends StaticSense{
  override val words: String = "解决 克服"
}
