/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:To.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.thing

import com.zpy.yearn.dict.meta.adv.prep.Prep
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/3/2.
  */
case class To(override val obj: TThing) extends Prep{
  //override val chStr: String = "对于 对"
}
