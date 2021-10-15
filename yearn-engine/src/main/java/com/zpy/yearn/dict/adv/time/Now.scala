/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Now.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.time

import com.zpy.yearn.dict.adv.meta.WhichDay
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/4/22.
  */
case class Now() extends WhichDay{
  override val timeContent: Int = 0
}
object Now extends StaticSense{
  override val words: String = "现在"
}
