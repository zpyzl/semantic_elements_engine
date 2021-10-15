/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Meet.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.concrete

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTIb

/**
  * Created by zpy on 2019/2/20.
  */
case class Meet(override val actor: TIb, override val objIb: TIb
                ) extends ActionVTIb{
  //override val chStr: String = "遇见 相遇"
}
