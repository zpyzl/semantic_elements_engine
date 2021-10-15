/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Common.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.adv.time.Often
import com.zpy.yearn.dict.basic.ib.sense.See
import com.zpy.yearn.dict.basic.normal.CommonIb
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.{TPred, TEntity}
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * Created by zpy on 2019/10/28.
  */
case class Common( ) extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left(Some(
      centralExplainer.fm match {
        case predCentral: TPred =>
          predCentral.copyAddMods( Set( Often()))
        case entity: TEntity[_] =>
          See( CommonIb(), Central()).which(Often())
      }
    ))
  }
}
