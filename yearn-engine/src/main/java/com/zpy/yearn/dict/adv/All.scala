/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:All.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.modifier.Definite
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 副词：都
  * Created by zpy on 2019/4/26.
  */
case class All() extends Adv  {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainReplaceMods(
      centralExplainer.fm.mods.filter(_!=this),
      sbj => sbj.definite = Definite.any,
      _=>{},
      None,
      this )
    true
  }

}
object All extends StaticSense{
  override val words: String = "都 全都 全"
}