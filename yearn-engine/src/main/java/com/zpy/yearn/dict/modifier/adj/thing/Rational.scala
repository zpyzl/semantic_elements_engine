/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Rational.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.logic.pred.ConformTo
import com.zpy.yearn.dict.basic.normal.NormalInfo
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * 合理的，合乎道理的，应该的
  *
  * 例如：他违章被罚款是应该的。
  *
  * Created by zpy on 2019/9/27.
  */
case class Rational( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] =
    Left( Some(ConformTo( Central(), NormalInfo(Central()))))

  //override val chStr: String = "合理 应该"
}
