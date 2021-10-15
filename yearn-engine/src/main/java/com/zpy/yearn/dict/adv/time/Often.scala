/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Often.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv.time

import com.zpy.yearn.dict.adv.Very
import com.zpy.yearn.dict.basic.amount.absolute.Many
import com.zpy.yearn.dict.basic.time.Repeatedly
import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.thing.Explainer

/**
  * Created by zpy on 2019/10/5.
  */
case class Often() extends Adv {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainForModExplain( Set( Repeatedly(Right(Many().which(Very())))), None,this)
    true
  }
}
