/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Brave.scala
 * Date:2020/3/8 下午6:06
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.ib.TAction
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.IbCentral
import com.zpy.yearn.dict.meta.pred.PredExtractor
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.thing.Bad
import com.zpy.yearn.dict.verb.action.Do
import com.zpy.yearn.structure.sense.StaticSense

/**
  * Created by zpy on 2020/3/8.
  */
case class Brave() extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left(Some(
      Do(IbCentral()).whichIs( Bad().to(IbCentral()))
    )
    )
  }
}
object Brave extends PredExtractor with StaticSense {
  override def extract(pred: TPred): Option[TPred] = {
    pred match {
      case Be(doSth: TAction, Bad()) => {
        Some(Be(doSth.actor, Brave()))
      }
      case _ => None
    }
  }

  override val words: String = "勇敢"

  override def remember2AddInInferers记得在Inferers里面添加暂时没时间研究了(): Unit = {}
}