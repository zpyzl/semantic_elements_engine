/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Repeatedly.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.time

import com.zpy.yearn.dict.basic.amount.absolute.OneOrMore
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.modifier.TQuantifier

/**
  * Created by zpy on 2019/10/8.
  */
case class Repeatedly(times : Either[Int, TQuantifier] = Right( OneOrMore())
                      ) extends Adv {

}
