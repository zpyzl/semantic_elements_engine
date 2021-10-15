/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:TenseEnum.scala
 * Date:2020/4/3 上午10:19
 * Author: 赵鹏阳
 */

package com.zpy.yearn.structure.tense

import com.zpy.yearn.structure.tense.Tense.Tense

/**
  * Created by zpy on 2020/4/3.
  */
abstract class TenseEnum extends Enumeration {
  def past: Set[Tense]
}
