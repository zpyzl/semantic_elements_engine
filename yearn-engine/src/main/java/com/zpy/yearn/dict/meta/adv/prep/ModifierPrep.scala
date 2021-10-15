/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:ModifierPrep.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.prep

import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Modifier
import com.zpy.yearn.dict.meta.thing.TThing

/**
  * 作定语的介词
  * Created by zpy on 2019/12/30.
  */
trait ModifierPrep extends  Modifier with Prep  {
  def explain(pred: TPred, central: TThing): Unit = selfMeaning(pred,central)

  def selfMeaning(pred: TPred, central: TThing): Unit
}
