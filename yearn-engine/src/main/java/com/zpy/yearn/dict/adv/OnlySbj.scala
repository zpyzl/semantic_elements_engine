/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:OnlySbj.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/5/16.
  */
case class OnlySbj( ) extends Adv {

  //override def toString: String = "only " + pred.toString
  override def advbMeaning(centralExplainer: Explainer): Boolean = false

}
object OnlySbj extends StaticSense{
  override val words: String = "只有"
}