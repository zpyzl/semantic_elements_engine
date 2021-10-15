/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TakeEffect.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.vi

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 起作用
  * Created by zpy on 2019/4/15.
  */
case class TakeEffect(override val sbj: TThing
                        ) extends VI {
  //override val chStr: String = "起作用"
}
