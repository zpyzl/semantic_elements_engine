/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Dangerous.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.logic.pred.possibility.Can
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.verb.vt

/**
  * 机器可能会不正常吗？
  * 语料：机器处于危险中
  * 答：会
  *
  * 问：为什么X会不正常？
  * 语料：Y对于X是危险的
  * 答：可能是Y
  *
  * Created by zpy on 2019/10/28.
  */
case class Dangerous() extends Adj{
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv]): Either[Option[TThing], Adj] = {
    Left( Some( vt.Damage( Central(), to.get).which(Can())) )
  }
}
