/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:About.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.relation

import com.zpy.yearn.dict.meta.adv.prep.ModifierPrep
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 关于
  * Created by zpy on 2019/9/23.
  */
case class About(override val obj: TThing) extends ModifierPrep{
  //override val chStr: String = "关于"
  override def selfMeaning(pred: TPred, central: TThing): Unit = {}
}
