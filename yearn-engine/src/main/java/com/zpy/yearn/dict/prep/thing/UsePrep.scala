/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:UsePrep.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.prep.thing

import com.zpy.yearn.dict.meta.adv.prep.{AdvbPrep, NoneAdvbPrep}
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2019/6/24.
  */
case class UsePrep(override val obj: TThing) extends AdvbPrep {

  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainForModExplain(Set(), None,this)
    target(centralExplainer.fm.asInstanceOf[TPred]).use_+=(obj)
    true
  }
  def target(predicate: TPred): TAction =
       predicate.asInstanceOf[TAction]

  //override val chStr: String = "用 凭借"
}
