/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Complete.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.amount.absolute.All
import com.zpy.yearn.dict.basic.belonging.{Contain, Part}
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.structure.sense.StaticSense

/**
  *
  * Created by zpy on 2019/4/16.
  */
case class Complete( ) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ):Either[Option[TThing], Adj] = {
    Left(Some(
      Contain(Central(), Part().which(Normal(),All()).of(Central()))))
  }
/*  override def eqvlByAdv_=(adv: AdjAdv): Option[Pred] = {
    for (adjSbj <- adv.asAdj(sbj)) yield
      vt.Contain(sbj, All(Part().of(adjSbj)))
  }*/


  /*
  “这个不完整”“缺少什么？”这样的对话是第二个人想让它完整，所以问缺少什么，以便弥补
   */
  //override val chStr: String = "完整"
}
object Complete extends StaticSense{
  override val words: String = "完整"
}