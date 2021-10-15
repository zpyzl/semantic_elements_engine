/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Before.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.time

import com.zpy.yearn.dict.meta.adv.prep.TimePrep
import com.zpy.yearn.dict.meta.adv.time.Time

/**
  * Created by zpy on 2019/4/21.
  */
case class Before(override val time: Time ) extends TimePrep {
  //override val chStr: String = "之前 以前"
}
