/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Badly.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.dict.modifier.adj.thing.{Bad, Extreme}
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 严重地，表示造成很不好的
  * Created by zpy on 2019/9/28.
  */
case class Badly( ) extends Adv {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    centralExplainer.explainForModExplain( Set(Extreme(),Bad()), None,this)
    true
  }

}
object Badly extends StaticSense{
  override val words: String = "严重"
}
