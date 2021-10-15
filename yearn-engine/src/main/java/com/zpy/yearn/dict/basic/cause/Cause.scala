package com.zpy.yearn.dict.basic.cause

import com.zpy.yearn.dict.meta.hasArgs.{Mapping, TPred}
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2020/5/19.
  */
case class Cause(override val reason1: TThing,
                 override val result: TThing) extends Mapping {

  def this(reasons: Seq[TThing], result: TThing){
    this(reasons.head, result)
    setReasons(reasons, result)
  }

  def this(rs1: TThing, rs2: TThing, result: TThing){
    this(rs1, result)
    reason2 = Some(rs2)
  }
  def this(rs1: TThing, rs2: TThing, rs3: TThing ,result: TThing){
    this(rs1, result)
    reason2 = Some(rs2)
    reason3 = Some(rs3)
  }
  setFrom

  override def verbMeaning(pred: TPred): Set[TPred] = Set()

}
