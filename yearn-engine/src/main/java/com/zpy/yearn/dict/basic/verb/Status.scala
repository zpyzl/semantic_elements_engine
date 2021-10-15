package com.zpy.yearn.dict.basic.verb

import com.zpy.yearn.dict.meta.predicate.continuous.VC
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/5/24.
  */
case class Status(override val sbj: TThing) extends VI with VC{
  this.traitForAssignable = Some( classOf[VC])
}
