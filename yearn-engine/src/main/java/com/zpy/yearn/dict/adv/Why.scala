/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Why.scala
 * Date:2020/1/2 下午2:36
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/1/2.
  */
case class Why() extends Adv{

}
object Why extends StaticSense{
  override val words: String = "为什么 为何"
}
