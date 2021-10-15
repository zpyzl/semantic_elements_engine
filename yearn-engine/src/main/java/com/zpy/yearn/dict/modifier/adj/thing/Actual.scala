/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Actual.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.ib.Thought
import com.zpy.yearn.dict.basic.logic.Be
import com.zpy.yearn.dict.basic.logic.conj.AllOf
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}

/**
  * 实际的
  *
  * 造句：
  * 他只是猜想 =》他不知道实际情况
  *
  *
  * Created by zpy on 2019/10/6.
  */
case class Actual() extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] =
    Left(Some(
      AllOf( Set(
        Be(Central(), Thought()).not(),
        Be(Central(), Virtual()).not())))) //不是想的也不是虚拟的
}
