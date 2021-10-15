/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Plural.scala
 * Date:2020/1/3 下午12:01
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.quantity

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.thing.TThing
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2019/11/8.
  */
case class Plural[T <: TThing](entity: TEntity[T]) extends TEntityOfThing
