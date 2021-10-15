/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:IsStatement.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing.entity

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 表示一个陈述句的概念性名词
  * Created by zpy on 2019/1/2.
  */
trait IsStatement extends TEntityOfThing {
  val is: TPred
}
