/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Or.scala
 * Date:2020/1/3 下午12:04
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.conj

import com.zpy.yearn.dict.basic.amount.absolute.LessThanNormal
import com.zpy.yearn.dict.basic.ib.effort.Effort
import com.zpy.yearn.dict.basic.time.Period
import com.zpy.yearn.dict.meta.predicate.hasObjectOrNot.twoArgs.VT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/10/29.
  */
case class Or(override val sbj: TThing, override val obj: TThing
              ) extends VT  {


}
object Or{
  /**
    * a or b or (a and b)
    *
    * @param a
    * @param b
    * @return
    */
  def exhausted(a: TThing, b: TThing): Or = {
    Or(
      Or(
        Effort().which(LessThanNormal()),
        Period().which(LessThanNormal())),
      And(
        Effort().which(LessThanNormal()),
        Period().which(LessThanNormal()))
    )
  }
}
