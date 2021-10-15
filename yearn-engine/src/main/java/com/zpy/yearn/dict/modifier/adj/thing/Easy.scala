/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Easy.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.amount.absolute.LessThanNormal
import com.zpy.yearn.dict.basic.ib.effort.Effort
import com.zpy.yearn.dict.basic.logic.conj.Or
import com.zpy.yearn.dict.basic.time.Period
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * 容易：
  * 需要较少精力时间。
  *
  * Created by zpy on 2019/9/22.
  */
case class Easy( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    Left(
      Some(new Cause( Seq(
          Or.exhausted(
            Effort().which(LessThanNormal()),
            Period().which(LessThanNormal()))
      ),  Central())) )//只需要较少的时间、精力
  }

  //override val chStr: String = "容易"
}
