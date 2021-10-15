package com.zpy.yearn.dict.basic.verb

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.oneArg.VI
import com.zpy.yearn.dict.meta.predicate.linkOrNot.VV

/**
  * Created by zpy on 2020/5/24.
  */
case class Pred() extends VI{
  this.traitForAssignable = Some(classOf[Pred])
}
