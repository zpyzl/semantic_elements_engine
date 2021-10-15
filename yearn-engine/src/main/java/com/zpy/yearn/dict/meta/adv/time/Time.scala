/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Time.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.time

import com.zpy.yearn.dict.meta.thing.entity.ownerType.TEntityOfThing

/**
  * 时间点
  * Created by zpy on 2019/4/18.
  */
trait Time extends TEntityOfThing with Comparable[Time] {
  val timeContent: Int

  override def compareTo(o: Time): Int =
    timeContent - o.timeContent
}
