/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:To.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.whom

import com.zpy.yearn.dict.meta.adv.prep.PrepWhom
import com.zpy.yearn.dict.meta.ib.TIb

/**
  * Created by zpy on 2019/3/1.
  */
case class To(override val whom: TIb) extends PrepWhom{
  //override val chStr: String = "对于 对"
}
