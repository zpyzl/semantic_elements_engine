/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:AdvbPrep.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.meta.adv.prep

import com.zpy.yearn.dict.meta.adv.Advb
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.thing.Explainer

/**
  * Created by zpy on 2019/4/24.
  */
trait AdvbPrep extends  Advb with Prep {
  /* override def copyTo(that: Thing): Unit = {
    that.m_+=( this.newInstance(obj).asInstanceOf[Sense ])

  }*/
  override def explain(fe: TPred): Boolean = super[Prep].explain(fe)

  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    super.advbMeaning(centralExplainer)
  }
}
