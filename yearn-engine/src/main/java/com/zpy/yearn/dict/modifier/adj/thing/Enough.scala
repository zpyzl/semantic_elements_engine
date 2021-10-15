/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Enough.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.amount.relative
import com.zpy.yearn.dict.basic.amount.relative.MoreAmount
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.noun.abstractNoun.Thing
import com.zpy.yearn.dict.verb.vt

/**
  * 他工作需要什么？
  * 他有足够的X去工作
  * 答：需要X
  *
  * Created by zpy on 2019/10/31.
  */
case class Enough() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    val sth = Thing().some()
    Left(Some(
      Be( Central(),
        MoreAmount(relative.Than(
          sth.which(vt.Need( to.get, Central())))
        ))))
  }
}
