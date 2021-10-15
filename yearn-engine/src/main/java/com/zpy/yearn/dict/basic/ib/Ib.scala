package com.zpy.yearn.dict.basic.ib

import com.zpy.yearn.dict.meta.hasArgs.TEntity
import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * Created by zpy on 2020/5/25.
  */
case class Ib() extends TIb{
  this.traitForAssignable = Some(classOf[TIb])
}
