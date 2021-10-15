/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Good.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.basic.ib

import com.zpy.yearn.dict.basic.desire.Desire
import com.zpy.yearn.dict.basic.ib.sense.SenseAction
import com.zpy.yearn.dict.basic.logic.conj.Or
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.verb.vt

/**
  * Created by zpy on 2019/4/4.
  */
case class Good(
                 ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] =
    Left(Some(
      Or(
        vt.Satisfy(Central(), Desire().of( ib (pred)) ),
        SenseAction( ib(pred), sense.Feeling( Set(Positive()))  )  //除了包括满足显式欲望，还包括满足隐形欲望，即感官上的正向的感觉，比如“美”“香”
      )))
  //override val chStr: String = "好 棒"
}

