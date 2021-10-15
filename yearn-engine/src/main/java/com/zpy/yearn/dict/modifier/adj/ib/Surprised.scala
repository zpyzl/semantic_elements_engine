/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Surprised.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.ib

import com.zpy.yearn.dict.basic.belonging
import com.zpy.yearn.dict.basic.ib.sense.Feeling
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.modifier.adj.thing.Unexpected
import com.zpy.yearn.dict.noun.abstractNoun.Thing

/**
  * Created by zpy on 2019/10/29.
  */
case class Surprised() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left( Some(
      belonging.Have(Central(),
        Feeling().a().because(Thing().some().which(Unexpected())))
    ) )
  }
}
