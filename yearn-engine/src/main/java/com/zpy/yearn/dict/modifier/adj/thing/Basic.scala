/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:Basic.scala
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn.dict.modifier.adj.thing

import com.zpy.yearn.dict.basic.amount.absolute.Many
import com.zpy.yearn.dict.basic.belonging.Part
import com.zpy.yearn.dict.meta.adv.AdjAdv
import com.zpy.yearn.dict.basic.cause.Cause
import com.zpy.yearn.dict.meta.hasArgs.TPred
import com.zpy.yearn.dict.meta.modifier.Adj
import com.zpy.yearn.dict.meta.modifier.attrClause.Central
import com.zpy.yearn.dict.meta.thing.{Explainer, TThing}
import com.zpy.yearn.dict.prep.thing.In
import com.zpy.yearn.structure.sense.StaticSense

/**
  * 组成其他的
  *
  * 是组成，还是因果？
  *
  * 投篮是打篮球最基本的动作
  *
  * 会投篮才会打篮球：因果
  * 打篮球包括投篮：组成
  *
  * 和人交往最基本的是尊重
  *
  * 组成也是
  *
  * Created by zpy on 2019/7/19.
  */
case class Basic(inScope: In) extends Adj {
  override def adjMeaning(pred: TPred, centralExplainer: Explainer, advs: Set[AdjAdv] ): Either[Option[TThing], Adj] = {
    val scope = inScope.obj
    Left(Some(
      Cause((Central()), Part().which(Many()).otherThan(Central()).of(scope) )) )//many other in )
  }

  //override val chStr: String = "基本"
}
object Basic extends StaticSense{
  override val words: String = "基本"
}