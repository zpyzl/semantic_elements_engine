/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Calmly.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.ib

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/8/19.
  */
case class Calmly( ) extends Adv {
}
object Calmly extends StaticSense{
  override val words: String = "冷静 平静"
}
