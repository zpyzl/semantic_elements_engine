/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ModifierFT.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.modifier

import com.zpy.yearn.dict.meta.other.SenseFT
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 定语词
  * Created by zpy on 2018/10/28.
  */
trait ModifierFT extends SenseFT{
  val central: TThing

  //def centralFs: Things = twp.introducer.get
  //override val meaningF: Thing = central

}