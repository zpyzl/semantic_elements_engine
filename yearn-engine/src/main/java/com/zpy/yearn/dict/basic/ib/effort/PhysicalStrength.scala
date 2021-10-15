/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:PhysicalStrength.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib.effort

import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/10/29.
  */
case class PhysicalStrength() extends TEntityOfIb{

}
object PhysicalStrength extends StaticSense{
  override val words: String = "体力"
}
