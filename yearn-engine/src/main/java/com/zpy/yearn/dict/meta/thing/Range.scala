/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Range.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.thing

import com.zpy.yearn.dict.meta.adv.prep.TimePrep

/**
  * Created by zpy on 2019/4/23.
  */
trait Range {

  def contains(timePrep: TimePrep): Boolean

}
