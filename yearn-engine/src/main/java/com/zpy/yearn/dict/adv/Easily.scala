/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Easily.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.adv

import com.zpy.yearn.dict.meta.adv.Adv
import com.zpy.yearn.dict.meta.predicate.linkOrNot.Verb
import com.zpy.yearn.dict.meta.thing.Explainer
import com.zpy.yearn.structure.sense.StaticSense
import com.zpy.yearn.structure.tense.Tense

/**
  * Created by zpy on 2019/6/5.
  */
case class Easily( ) extends Adv {
  override def advbMeaning(centralExplainer: Explainer): Boolean = {
    if (centralExplainer.fm.asInstanceOf[Verb].tense == Tense.SimplePresent ) {
      centralExplainer.explainForModExplain( Set( QuitePossibly()),None,this)
      true
    }else false

  }

}
object Easily extends StaticSense{
  override val words: String = "容易"
}