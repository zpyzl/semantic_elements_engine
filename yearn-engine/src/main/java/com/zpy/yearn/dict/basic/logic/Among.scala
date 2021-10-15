/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Among.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.logic

import com.zpy.yearn.dict.meta.adv.prep.AdvbPrep
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2019/10/5.
  */
case class Among(override val obj: TThing) extends AdvbPrep {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    //todo 任意两个间
    false
  }
}
