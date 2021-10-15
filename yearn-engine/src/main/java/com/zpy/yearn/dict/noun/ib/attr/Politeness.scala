/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Politeness.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.ib.attr

import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfIb
import com.zpy.yearn.dict.verb.action.`trait`.TPoliteness

/**
  * 礼貌行为
  * Created by zpy on 2019/2/20.
  */
case class Politeness( ) extends TEntityOfIb with TPoliteness{
  //override val chStr: String = "礼貌"
}
