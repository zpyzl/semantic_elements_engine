/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TStatements.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.entity

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 代表一系列陈述的概念。比如“规则”
  * Created by zpy on 2019/9/26.
  */
trait TStatement extends TEntityOfThing {
  val statements: Seq[TPred] = Seq()
}
