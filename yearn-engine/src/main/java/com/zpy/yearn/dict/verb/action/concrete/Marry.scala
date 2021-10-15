/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Marry.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.verb.action.concrete

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.ActionVTIb

/**
  * 结婚
  * Created by zpy on 2019/4/27.
  */
case class Marry(override val actor: TIb, override val objIb: TIb
                 ) extends ActionVTIb {
  //override val chStr: String = "结婚"
}
