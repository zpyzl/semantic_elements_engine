/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Infer.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic.pred

import com.zpy.yearn.dict.conj.Conj
import com.zpy.yearn.dict.meta.hasArgs.{Mapping, TPred, V4args}
import com.zpy.yearn.dict.meta.thing.{SourceType, TThing}
import com.zpy.yearn.dict.pronoun.nothing.Nothing

/**
  * 推理，由。。推出。。
  * Created by zpy on 2019/10/6.
  */
case class Infer(override val reason1: TThing,
                 override val result: TThing) extends Mapping  {

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
}
