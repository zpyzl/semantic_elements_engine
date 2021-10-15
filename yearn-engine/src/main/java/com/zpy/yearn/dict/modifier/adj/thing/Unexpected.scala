/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Unexpected.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.verb.Pred
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.prep.thing
import com.zpy.yearn.dict.verb.action.alias

/**
  * Created by zpy on 2019/10/29.
  */
case class Unexpected() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    val sth = Pred()
    Left( Some(
      Be(
        Central(),
        thing.In( sth.which(alias.Expect( ib(pred), Central() ))))
        .not()
    ))
  }
}
