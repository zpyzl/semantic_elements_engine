/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Always.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.time

import com.zpy.yearn.dict.basic.amount.absolute.Many
import com.zpy.yearn.dict.basic.time.Repeatedly
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.dict.modifier.adj.thing.Extreme

/**
  * Created by zpy on 2019/10/29.
  */
case class Always() extends Adv {


  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainForModExplain(Set(Repeatedly(Right(Many().which(Extreme())))), None,this)
    true
  }
}
