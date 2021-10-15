/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:After.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.time

import com.zpy.yearn.dict.meta.adv.prep.TimePrep
import com.zpy.yearn.dict.meta.adv.time.Time
import com.zpy.yearn.dict.meta.thing.Range

/**
  * Created by zpy on 2019/4/21.
  */
case class After(override val time: Time ) extends TimePrep with Range{

  /*
  p1 prop:
  if after t1 p1, t1 < t2, then t2 p1

  to judge p = Now(p1)
  p exists?
  if not,  remove adv, p1 exists?

   */

  override def contains(timePrep: TimePrep): Boolean = {
    timePrep match {
      case at: At => at.time.timeContent > time.timeContent
    }
  }

  //override val chStr: String = "之后 以后"
}
