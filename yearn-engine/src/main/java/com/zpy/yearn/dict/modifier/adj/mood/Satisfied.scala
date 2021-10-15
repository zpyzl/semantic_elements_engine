/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Satisfied.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.mood

import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.IbCentral
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/12/28.
  */
case class Satisfied() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left( Some(//todo 声明一种definite: a or s，包括单数和复数
      vt.Satisfy(Thing().some(), Desire().a().of(IbCentral()) )
    ))
  }
}
object Satisfied extends StaticSense{
  override val words: String = "满意"
}
