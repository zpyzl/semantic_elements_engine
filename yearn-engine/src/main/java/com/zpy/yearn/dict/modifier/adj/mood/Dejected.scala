/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Dejected.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.mood

import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.ib.Mood
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.IbCentral
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.meta.TBad
import com.zpy.yearn.dict.modifier.adj.thing.Bad
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt.Satisfy
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/5/14.
  */
case class Dejected() extends TBad{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    Left(Some(
      logic.Be(
        Mood().of(IbCentral() ),
        Bad()
           ).because(
        Satisfy(Thing().some(), Desire().of( IbCentral()))
          .did().not() )))
  }

  //override val chStr: String = "沮丧"
}
object Dejected extends StaticSense{
  override val words: String = "沮丧"
}
