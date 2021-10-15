/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdverbialFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv

import com.zpy.yearn.dict.meta.other.SenseFT

/**
  * 状语
  * Created by zpy on 2019/3/23.
  */
trait AdverbialFT extends SenseFT {
  val advb: Option[Advb]
}
