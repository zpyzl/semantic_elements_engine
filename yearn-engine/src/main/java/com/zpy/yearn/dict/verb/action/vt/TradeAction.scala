package com.zpy.yearn.dict.verb.action.vt

import com.zpy.yearn.dict.meta.ib.TIb
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.threeArgs.ActionV3args
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2021/5/5.
  */
case class TradeAction(override val actor: TIb,
                       override val obj: TThing,
                       override val arg3: TThing) extends ActionV3args{
  val use = arg3

}
