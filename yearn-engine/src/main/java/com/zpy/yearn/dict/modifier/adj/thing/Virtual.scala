/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Virtual.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.ib.Ib
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.relation.BeSimilarTo
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.verb.action.vt

/**
  * 虚拟的
  * Created by zpy on 2019/10/6.
  */
case class Virtual() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    Left(Some(
      pred match {
        case be: Be =>
          BeSimilarTo(pred.sbj, Central()).which(vt.Make(Ib(), pred.sbj))
        case _ =>throw new RuntimeException("虚拟：除了be,其他用法暂不支持")//todo
      }
    ))
  }
}
