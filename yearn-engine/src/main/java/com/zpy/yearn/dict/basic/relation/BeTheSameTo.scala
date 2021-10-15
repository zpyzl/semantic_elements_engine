/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:BeTheSameTo.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.relation

import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/6.
  */
case class BeTheSameTo(override val sbj: TThing, override val obj: TThing ) extends VT {

}
