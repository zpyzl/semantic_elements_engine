/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:In.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.thing

import com.zpy.yearn.dict.meta.adv.prep.AdvbPrep
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * Created by zpy on 2019/7/21.
  */
case class In(override val obj: TThing) extends AdvbPrep {
  //override val chStr: String = "里 内"
}
