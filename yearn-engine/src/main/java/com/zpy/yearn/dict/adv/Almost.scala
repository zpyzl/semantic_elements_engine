/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Almost.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.thing.Explainer

/**
  * Created by zpy on 2019/10/29.
  */
case class Almost() extends Adv {

  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainForModExplain( Set(
      //todo
    ), None, this )
    true
  }
}
