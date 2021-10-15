/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Miserable.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.adv.Very
import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.ib.Mood
import com.zpy.yearn.dict.basic.logic
import com.zpy.yearn.dict.basic.logic.pred.Important
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.attrClause.IbCentral
import com.zpy.yearn.dict.meta.modifier.{Adj, TIbAdj}
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.thing.Bad
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt.Satisfy
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2019/11/5.
  */
case class Miserable() extends TIbAdj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left( Some(
      logic.Be(
        Mood().of(IbCentral() ),
        Bad().which(Very())
      ).because( Satisfy(Thing().some(), Desire().which(Important()).of( IbCentral())).did().not() )
    ))
  }
}
object Miserable extends StaticSense{
  override val words: String = "痛苦"
}