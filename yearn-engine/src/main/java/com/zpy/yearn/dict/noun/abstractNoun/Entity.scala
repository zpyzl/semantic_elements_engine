/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Entity.scala
 * Date:2020/4/27 下午4:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.noun.abstractNoun

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2020/4/27.
  */
case class Entity() extends TEntityOfThing{
  this.traitForAssignable = Some(classOf[TEntity[_]])
}
